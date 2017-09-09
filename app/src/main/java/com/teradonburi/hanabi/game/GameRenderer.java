package com.teradonburi.hanabi.game;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.teradonburi.hanabi.game.geometory.Geometory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by daiki on 2017/09/03.
 */

public class GameRenderer implements GLSurfaceView.Renderer {

    private Map<String,Geometory> geometories = new HashMap<>();
    private GameRendererEvent rendererEvent;

    @Inject
    public GameRenderer(){}

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        if (rendererEvent != null) {
            rendererEvent.onGameInit();
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        if (rendererEvent != null) {
            rendererEvent.onGameLoop();
        }

        // 背景色(R,G,B,ALPHA)
        GLES20.glClearColor(0.0f, 0.0f, 1.0f, 1);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        for(Map.Entry<String,Geometory> entry:geometories.entrySet()){
            entry.getValue().draw();
        }
    }

    public void setRendererEvent(GameRendererEvent rendererEvent) {
        this.rendererEvent = rendererEvent;
    }


    public void addGeometory(Geometory geometory){
        geometories.put(geometory.getId(),geometory);
    }

    public void removeGeometory(Geometory geometory){
        if(geometories.containsKey(geometory.getId())){
            geometories.remove(geometory.getId());
        }
    }

    public void clearGeometory(){
        geometories.clear();
    }



    /*
    private float[] mModelMatrix = new float[16];       // ワールド行列
    private float[] mViewMatrix = new float[16];        // ビュー行列
    private float[] mProjectionMatrix = new float[16];  // 射影行列
    private float[] mMVPMatrix = new float[16];         // これらの積行列

    private final FloatBuffer mTriangleVertices;  // 頂点バッファ

    private int mMVPMatrixHandle;  // u_MVPMatrixのハンドル
    private int mPositionHandle;   // a_Positionのハンドル
    private int mColorHandle;      // a_Colorのハンドル

    private final int mBytesPerFloat = 4;                                  // floatのバイト数
    private final int mStrideBytes = 7 * mBytesPerFloat;                   // ストライドバイト数
    private final int mPositionOffset = 0;                                 // 位置情報の先頭位置
    private final int mPositionDataSize = 3;                               // 位置情報のデータサイズ
    private final int mColorOffset = mPositionOffset + mPositionDataSize;  // 色情報の先頭位置
    private final int mColorDataSize = 4;                                  // 色情報のデータサイズ

    public MyRenderer() {
        // 頂点バッファ生成
        final float[] triangleVerticesData = {
                // 各頂点情報
                // (座標属性) X, Y, Z,
                // (色属性) R, G, B, A

                -0.5f, -0.25f, 0.0f,
                1.0f, 0.0f, 0.0f, 1.0f,

                0.5f, -0.25f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f,

                0.0f, 0.559016994f, 0.0f,
                0.0f, 1.0f, 0.0f, 1.0f
        };
        // バッファを確保し、バイトオーダーをネイティブに合わせる(Javaとネイティブではバイトオーダーが異なる)
        mTriangleVertices = ByteBuffer.allocateDirect(triangleVerticesData.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        mTriangleVertices.put(triangleVerticesData).position(0);  // データをバッファへ
    }

    // サーフェスが初めて作成された際・再作成された際に呼ばれる
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // 描画領域を黒色でクリア

        // カメラ(ビュー行列)を設定
        final float[] eye = {0.0f, 0.0f, 1.5f};
        final float[] look = {0.0f, 0.0f, -5.0f};
        final float[] up = {0.0f, 1.0f, 0.0f};
        Matrix.setLookAtM(mViewMatrix, 0, eye[0], eye[1], eye[2], look[0], look[1], look[2], up[0], up[1], up[2]);

        // バーテックスシェーダ
        final String vertexShader =
                "uniform mat4 u_MVPMatrix;      \n"
                        + "attribute vec4 a_Position;     \n"
                        + "attribute vec4 a_Color;        \n"

                        + "varying vec4 v_Color;          \n"

                        + "void main()                    \n"
                        + "{                              \n"
                        + "   v_Color = a_Color;          \n"

                        + "   gl_Position = u_MVPMatrix   \n"
                        + "               * a_Position;   \n"
                        + "}                              \n";

        // フラグメントシェーダ
        final String fragmentShader =
                "precision mediump float;       \n"

                        + "varying vec4 v_Color;          \n"

                        + "void main()                    \n"
                        + "{                              \n"
                        + "   gl_FragColor = v_Color;     \n"
                        + "}                              \n";

        // バーテックスシェーダをコンパイル
        int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        if (vertexShaderHandle != 0) {
            GLES20.glShaderSource(vertexShaderHandle, vertexShader);  // シェーダソースを送信し
            GLES20.glCompileShader(vertexShaderHandle);  // コンパイル

            // コンパイル結果のチェック
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(vertexShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

            if (compileStatus[0] == 0) {
                // コンパイル失敗
                GLES20.glDeleteShader(vertexShaderHandle);
                vertexShaderHandle = 0;
            }
        }
        if (vertexShaderHandle == 0) {
            throw new RuntimeException("Error creating vertex shader.");
        }

        // フラグメントシェーダをコンパイル
        int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        if (fragmentShaderHandle != 0) {
            GLES20.glShaderSource(fragmentShaderHandle, fragmentShader);
            GLES20.glCompileShader(fragmentShaderHandle);

            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(fragmentShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

            if (compileStatus[0] == 0) {
                GLES20.glDeleteShader(fragmentShaderHandle);
                fragmentShaderHandle = 0;
            }
        }
        if (fragmentShaderHandle == 0) {
            throw new RuntimeException("Error creating fragment shader.");
        }

        // シェーダプログラムをリンク
        int programHandle = GLES20.glCreateProgram();
        if (programHandle != 0) {
            GLES20.glAttachShader(programHandle, vertexShaderHandle);  // バーテックスシェーダをアタッチ
            GLES20.glAttachShader(programHandle, fragmentShaderHandle);  // フラグメントシェーダをアタッチ
            GLES20.glBindAttribLocation(programHandle, 0, "a_Position");  // attributeのindexを設定
            GLES20.glBindAttribLocation(programHandle, 1, "a_Color");  // attributeのindexを設定
            GLES20.glLinkProgram(programHandle);  // バーテックスシェーダとフラグメントシェーダをプログラムへリンク

            // リンク結果のチェック
            final int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);

            if (linkStatus[0] == 0) {
                // リンク失敗
                GLES20.glDeleteProgram(programHandle);
                programHandle = 0;
            }
        }
        if (programHandle == 0) {
            throw new RuntimeException("Error creating program.");
        }

        // ハンドル(ポインタ)の取得
        mMVPMatrixHandle = GLES20.glGetUniformLocation(programHandle, "u_MVPMatrix");
        mPositionHandle = GLES20.glGetAttribLocation(programHandle, "a_Position");
        mColorHandle = GLES20.glGetAttribLocation(programHandle, "a_Color");

        // シェーダプログラム適用
        GLES20.glUseProgram(programHandle);
    }

    // 画面回転時など、サーフェスが変更された際に呼ばれる
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // スクリーンが変わり画角を変更する場合、射影行列を作り直す
        GLES20.glViewport(0, 0, width, height);

        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f;
        final float far = 10.0f;

        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
    }

    // 新しいフレームを描画する度に呼ばれる
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);  // バッファのクリア

        // プリミティブをアニメーション
        // 経過秒から回転角度を求める(10秒/周)
        long time = SystemClock.uptimeMillis() % 10000L;
        float angleInDegrees = (360.0f / 10000.0f) * ((int) time);

        // ワールド行列に対して回転をかける
        Matrix.setIdentityM(mModelMatrix, 0);  // 単位行列でリセット
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 0.0f, 0.0f, 1.0f);  // 回転行列
        drawTriangle(mTriangleVertices);
    }

    // 三角形を描画する
    private void drawTriangle(final FloatBuffer aTriangleBuffer) {
        // OpenGLに頂点バッファを渡す
        aTriangleBuffer.position(mPositionOffset);  // 頂点バッファを座標属性にセット
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false, mStrideBytes, aTriangleBuffer);  // ポインタと座標属性を結び付ける
        GLES20.glEnableVertexAttribArray(mPositionHandle);  // 座標属性有効

        aTriangleBuffer.position(mColorOffset);  // 頂点バッファを色属性にセット
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false, mStrideBytes, aTriangleBuffer);  // ポインタと色属性を結び付ける
        GLES20.glEnableVertexAttribArray(mColorHandle);  // 色属性有効

        // ワールド行列×ビュー行列×射影行列をセット
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);  // 三角形を描画
    }
    */

}
