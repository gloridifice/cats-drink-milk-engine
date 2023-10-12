#version 330 core
layout(location = 0) in vec3 position;
layout(location = 1) in vec2 texCoord0;
layout(location = 2) in vec3 normal;
layout(location = 3) in vec4 color;

out vec4 fColor;
out vec2 fTexCoord;

uniform mat4x4 uProjMatrix;
uniform mat4x4 uViewMatrix;
void main()
{
    vec4 pos = uProjMatrix * uViewMatrix * vec4(position, 1.0f);
    gl_Position = pos;
    fColor = color;
    fTexCoord = texCoord0;
}