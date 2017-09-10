package com.teradonburi.hanabi.game;

import android.opengl.GLSurfaceView;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.teradonburi.hanabi.entity.UserEntity;
import com.teradonburi.hanabi.game.geometory.Geometory;
import com.teradonburi.hanabi.game.geometory.Square;
import com.teradonburi.hanabi.game.geometory.Triangle;
import com.teradonburi.hanabi.game.math.Matrix44;
import com.teradonburi.hanabi.game.shader.ColorShader;
import com.teradonburi.hanabi.game.shader.TextureShader;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class GameMain implements GameRendererEvent{

    private final AppCompatActivity activity;
    private final SaveData saveData;
    private final GameRenderer renderer;
    private Matrix44 modelMatrix;
    private Camera camera;
    private Matrix44 projMatrix;
    private Matrix44 modelViewProjectionMatrix;

    private Triangle triangle;
    private Square square;
    private ColorShader colorShader;
    private TextureShader textureShader;

    private UserEntity userEntity;


    @Inject
    public GameMain(final AppCompatActivity activity,
                    final SaveData saveData,
                    final GameRenderer renderer){
        this.activity = activity;
        this.saveData = saveData;
        this.renderer = renderer;


        this.modelMatrix = new Matrix44();
        this.camera = new Camera();
        this.projMatrix = new Matrix44();
        this.modelViewProjectionMatrix = new Matrix44();

        this.triangle = new Triangle();
        this.square = new Square();
        this.colorShader = new ColorShader(activity);
        this.textureShader = new TextureShader(activity);

        this.renderer.setRendererEvent(this);
        loadUser();
    }

    private void loadUser()
    {
        // ユーザ生成
        if(!saveData.isExist(UserEntity.class)){
            userEntity = new UserEntity();
            userEntity.id = UserEntity.createUserID();
            saveData.save(userEntity);
        }
        // ユーザロード
        else{
            userEntity = saveData.load(UserEntity.class);
        }
    }

    public GLSurfaceView.Renderer getRenderer(){
        return renderer;
    }

    // サーフェスの初期化処理
    @Override
    public void onGameInit() {
        colorShader.loadShader();
        textureShader.loadShader();
        triangle.setShader(colorShader);
        square.setShader(textureShader);
        square.loadTexture();
        renderer.addGeometory(triangle);
        renderer.addGeometory(square);
    }

    // サーフェスの向きが変わった（画面が回転した）
    @Override
    public void onSurfaceChange(GL10 gl, int width, int height) {
        // 射影変換行列作成
       projMatrix = Matrix44.createProjectionMatrix((float)width/height,1.0f,1000.0f);
    }

    @Override
    public void onGameLoop() {

        // プリミティブをアニメーション
        // 経過秒から回転角度を求める(10秒/周)
        long time = SystemClock.uptimeMillis() % 10000L;
        float angleInDegrees = (360.0f / 10000.0f) * ((int) time);

        // ワールド行列に対して回転をかける
        modelMatrix.identity();
        modelMatrix.rotateZ(angleInDegrees);

        // カメラ更新
        camera.update();

        modelViewProjectionMatrix.identity();
        modelViewProjectionMatrix.mul(modelMatrix);
        modelViewProjectionMatrix.mul(camera.getViewMatrix());
        modelViewProjectionMatrix.mul(projMatrix);

        Geometory.setMVPMatrix(modelViewProjectionMatrix);
    }


}
