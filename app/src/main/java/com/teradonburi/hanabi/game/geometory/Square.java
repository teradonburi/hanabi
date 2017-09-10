package com.teradonburi.hanabi.game.geometory;

import android.opengl.GLES20;

import com.teradonburi.hanabi.R;
import com.teradonburi.hanabi.game.shader.TextureShader;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/10.
 */

@Lifecycle
public class Square extends Geometory{

    private final float vertices[] = {
            -1.0f, 1.0f, 0.0f,
            -1.0f,  0.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
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
    public Square() {
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

        if(shader instanceof TextureShader) {
            TextureShader textureShader = (TextureShader) shader;

            int handleTexture = textureShader.getUniformTexture();
            int handlePosition = textureShader.getAttributePosition();
            int handleTexcoord = textureShader.getAttributeTexcoord();

            textureShader.setTextureFiltering();
            textureShader.enableBlending(true);

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTexture);
            GLES20.glUniform1i(handleTexture, 0);

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

            textureShader.enableBlending(false);

        }

    }
}
