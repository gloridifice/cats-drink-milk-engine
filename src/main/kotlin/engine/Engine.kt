package engine

import engine.event.EngineEvent
import engine.io.ModelLoader
import engine.io.ShaderUniform
import engine.render.Camera
import engine.render.Material
import engine.render.MeshRenderer
import engine.system.GSystem
import engine.system.InputSystem
import glm_.vec4.Vec4
import gln.glViewport
import imgui.ConfigFlag
import imgui.DEBUG
import imgui.ImGui
import imgui.impl.gl.ImplGL3
import imgui.classes.Context
import imgui.div
import imgui.impl.glfw.ImplGlfw
import org.joml.Vector3f
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL33
import org.lwjgl.stb.STBImage
import uno.gl.GlWindow
import uno.glfw.*


class Engine {
    companion object{
        val INSTANCE = Engine()
    }

    val camera: Camera

    val gAppWindow: GlfwWindow
    val implGlfw: ImplGlfw
    val implGl3: ImplGL3
    val imguiCtx: Context

    val systems = arrayListOf<GSystem>(
        InputSystem()
    )
    val meshRenderer: MeshRenderer

    init {
        glfw {
            init()
            hints.context{
                debug = DEBUG
                ImplGL3.data.glslVersion = 150
                version = "3.2"
                profile = Hints.Context.Profile.Core      // 3.2+ only
                forwardComp = true  // Required on Mac
            }

        }
        //init glfw
        val glfwWindow = GlfwWindow(1280, 720, "Cat Drinks Milk")
        glfwWindow.keyCB = { window, key, scanCode, action, mods ->
            if (key == Key.ESCAPE && action == InputAction.Press){
                window.shouldClose = true
            }
        }
        gAppWindow = GlWindow(glfwWindow)

        gAppWindow.makeCurrent()
        glfw.swapInterval = VSync.ON

        GL33.glEnable(GL33.GL_DEPTH_TEST)
        STBImage.stbi_set_flip_vertically_on_load(true)

        //init imgui
        imguiCtx = Context()
        val io = imguiCtx.io
        io.configFlags /= ConfigFlag.NavEnableKeyboard

        ImGui.styleColorsLight()

        implGlfw = ImplGlfw(gAppWindow, true)
        implGl3 = ImplGL3()

        //engine

        val engineInit = EngineEvent.EngineInit(gAppWindow)
        systems.forEach { it.init(engineInit) }

        val meshInstance = ModelLoader.loadModelFile("first.obj")!![0].load()
        meshRenderer = MeshRenderer(
            Material("first", "first", "colormap.png"),
            meshInstance
        )
        camera = Camera(position = Vector3f(0f, 0f, 5f))
    }

    fun run() {
        loop()
        terminate()
    }

    private fun loop() {
        gAppWindow.loop {
            logic()
            render()
        }
    }
    private fun logic(){

    }

    private fun terminate() {
        implGl3.shutdown()
        implGlfw.shutdown()
        imguiCtx.destroy()

        GL.destroy()
        gAppWindow.destroy()
        glfw.terminate()
    }


    var clearColor = Vec4(0.2f, 0.2f, 0.5f, 1f)
    private fun render() {
        doRender {
            meshRenderer.render(
                arrayOf(
                    ShaderUniform("uViewMatrix", camera.viewMatrix()),
                    ShaderUniform("uProjMatrix", camera.projectionMatrix())
                )
            )
            with(ImGui){
                begin("Camera")
                colorEdit4("Color", clearColor)
//                ImGuiExpend.vector3Input("position", camera.position)
                end()
            }
        }
    }

    /**
     * This block will automatically glClearColor, new imgui frame, glViewport, renderDrawData
     */
    private inline fun doRender(block: () -> Unit){
        GL11.glClearColor(clearColor.x, clearColor.y, clearColor.z, clearColor.w)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
        implGl3.newFrame()
        implGlfw.newFrame()
        ImGui.newFrame()

        block()

        ImGui.render()
        glViewport(gAppWindow.framebufferSize)

        implGl3.renderDrawData(ImGui.drawData!!)
    }
}