uniform mat4 u_Matrix;
attribute vec4 position;
attribute vec2 texcoord;
varying vec2 texcoordVarying;

void main()
{
    gl_Position = u_Matrix * position;
    texcoordVarying = texcoord;
}