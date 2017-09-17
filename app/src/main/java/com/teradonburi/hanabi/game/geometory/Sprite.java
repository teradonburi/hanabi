package com.teradonburi.hanabi.game.geometory;

import android.opengl.GLES20;

import com.teradonburi.hanabi.R;
import com.teradonburi.hanabi.game.math.Matrix44;
import com.teradonburi.hanabi.game.shader.SpriteShader;
import com.teradonburi.hanabi.game.shader.TextureShader;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daikiterai on 2017/09/17.
 */

@Lifecycle
public class Sprite extends Geometory {

    private final float vertices[] = {
            -1.0f, 1.0f, -1.0f,
            -1.0f,  -1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
    };
    private final short indices[] = {
            0,1,2,3
    };
    private final float texcoords[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
    };

    int mTexture;

    @Inject
    public Sprite() {
        vertexBuffer.create(vertices);
        indexBuffer.create(indices);
        uvBuffer.create(texcoords);
    }

    public void loadTexture(){
        mTexture = shader.loadTexture(R.mipmap.ic_launcher);
    }

    @Override
    public void draw() {
        shader.attach();

        if(shader instanceof SpriteShader) {
            SpriteShader spriteShader = (SpriteShader) shader;

            int handleMatrix = spriteShader.getUniformMatrix();
            int handleTexture = spriteShader.getUniformTexture();
            int handlePosition = spriteShader.getAttributePosition();
            int handleTexcoord = spriteShader.getAttributeTexcoord();

            spriteShader.setTextureFiltering();
            spriteShader.enableBlending(true);

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTexture);
            GLES20.glUniform1i(handleTexture, 0);


            Matrix44 matrix44 = new Matrix44();
            final float screenW = 1920;
            final float screenH = 1080;
            final float targetW = 200;
            final float targetH = 200;
            final float x = 200;
            final float y = 200;
            matrix44.translate(x/screenW - 1,-y/screenH + 1,0);
            matrix44.scale(targetW/screenW ,targetH/screenH, 1);

            // 変換行列をセット
            GLES20.glUniformMatrix4fv(handleMatrix, 1, false, matrix44.m, 0);

            GLES20.glEnableVertexAttribArray(handlePosition);
            GLES20.glEnableVertexAttribArray(handleTexcoord);

            GLES20.glVertexAttribPointer(handleTexcoord, 2, GLES20.GL_FLOAT,
                    false, 0, uvBuffer.getBuffer());
            GLES20.glVertexAttribPointer(handlePosition, 3, GLES20.GL_FLOAT,
                    false, 0, vertexBuffer.getBuffer());

            GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP,
                    indexBuffer.getCapacity(),
                    GLES20.GL_UNSIGNED_SHORT,
                    indexBuffer.getBuffer());

            GLES20.glDisableVertexAttribArray(handlePosition);
            GLES20.glDisableVertexAttribArray(handleTexcoord);

            spriteShader.enableBlending(false);

        }

    }

}
