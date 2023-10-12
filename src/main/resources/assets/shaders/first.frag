#version 330 core
out vec4 FragColor;
in vec4 fColor;
in vec2 fTexCoord;

uniform sampler2D ourTexture;

void main()
{
    FragColor = vec4(.5, .5, .5, 1);
    FragColor = texture(ourTexture, fTexCoord);
}