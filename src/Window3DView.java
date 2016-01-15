import org.lwjgl.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.joml.*;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window3DView{
    //Callbacks
    static private GLFWErrorCallback errorCallback;
    static private GLFWMouseButtonCallback mouseCallback;
    static private GLFWKeyCallback   keyCallback;
    static private GLFWCursorPosCallback cursorPosCallback;

    //Window handle and information about the window
    static private long window;
    static private int WIDTH = 900;
    static private int HEIGHT = 600;

    //Container dimensions in # of cells
    static private int CONTAINER_WIDTH = 3;
    static private int CONTAINER_HEIGHT = 3;
    static private int CONTAINER_LENGHTH = 6;
    static private int[][][] CONTAINER_LATTICE = {};

    //Camera information
    static private Vector3f CAMERA_POS = new Vector3f(5,5,5); //Coordinates of the camera view target
    static private Vector2f CAMERA_ROT = new Vector2f(); //Angles for the camera ( YAW, PITCH )
    static private Vector3f CAMERA_FORWARD =  new Vector3f(); //Direction vector

    //Mouse information
    static boolean MOUSE_LEFT_DOWN = false;
    static boolean MOUSE_RIGHT_DOWN = false;
    static Vector2f MOUSE_LASTPOS = new Vector2f();

    static private ShaderProgram shaderProgram;

    //Transformation matrices
    static Matrix4f viewMatrix = new Matrix4f();
    static Matrix4f projectionMatrix = new Matrix4f();

    public static void main(String[] args) {
        run();
    }

    public static void setLattice(int[][][] lattice){
        CONTAINER_LATTICE = lattice;
    }

    private static void drawCube(float x, float y, float z){
        //Save the current matrix, because we will be translating the cubes
        glPushMatrix();
        glTranslatef(x + 0.5f, y + 0.5f, z + 0.5f);

        glBegin(GL_QUADS);
        glVertex3f( 0.5f, 0.5f,-0.5f);
        glVertex3f(-0.5f, 0.5f,-0.5f);
        glVertex3f(-0.5f, 0.5f, 0.5f);
        glVertex3f( 0.5f, 0.5f, 0.5f);
        glVertex3f( 0.5f,-0.5f, 0.5f);
        glVertex3f(-0.5f,-0.5f, 0.5f);
        glVertex3f(-0.5f,-0.5f,-0.5f);
        glVertex3f( 0.5f,-0.5f,-0.5f);
        glVertex3f( 0.5f, 0.5f, 0.5f);
        glVertex3f(-0.5f, 0.5f, 0.5f);
        glVertex3f(-0.5f,-0.5f, 0.5f);
        glVertex3f( 0.5f,-0.5f, 0.5f);
        glVertex3f( 0.5f,-0.5f,-0.5f);
        glVertex3f(-0.5f,-0.5f,-0.5f);
        glVertex3f(-0.5f, 0.5f,-0.5f);
        glVertex3f( 0.5f, 0.5f,-0.5f);
        glVertex3f(-0.5f, 0.5f, 0.5f);
        glVertex3f(-0.5f, 0.5f,-0.5f);
        glVertex3f(-0.5f,-0.5f,-0.5f);
        glVertex3f(-0.5f,-0.5f, 0.5f);
        glVertex3f( 0.5f, 0.5f,-0.5f);
        glVertex3f( 0.5f, 0.5f, 0.5f);
        glVertex3f( 0.5f,-0.5f, 0.5f);
        glVertex3f( 0.5f,-0.5f,-0.5f);
        glEnd();

        //Restore the matrix
        glPopMatrix();
    }

    private static void drawFloorGrid(){
        glBegin(GL_LINES);
        for(int i=-10;i<=10;i++) {
            glVertex3f(i,-10,0);
            glVertex3f(i,10,0);
            glVertex3f(-10,i,0);
            glVertex3f(10,i,0);
        };
        glEnd();
    }
    private static void initSwing(){
        new Thread( new Runnable() {
            @Override
            public void run() {
                ContainerPacker dialog = new ContainerPacker();
                dialog.pack();
                dialog.setVisible(true);
            }
        }).start();
    }

    public static void run() {
        try {
            init();
            initSwing();
            loop();

            glfwDestroyWindow(window);
            keyCallback.release();
            mouseCallback.release();
        } finally {
            glfwTerminate();
            errorCallback.release();
        }
    }

    private static void init() {
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        if ( glfwInit() != GLFW_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");


        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable


        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "BALLSAX!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");


        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GLFW_TRUE); // We will detect this in our rendering loop
            }
        });

        //Setup a mouse button callback
        glfwSetMouseButtonCallback(window, mouseCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
                    MOUSE_LEFT_DOWN = true;
                }else if(button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_RELEASE){
                    //Once the orbit has been adjusted, update the look direction
                    CAMERA_FORWARD.x = 15.0f * (float)Math.cos(CAMERA_ROT.x);
                    CAMERA_FORWARD.y = 15.0f * (float)Math.sin(CAMERA_ROT.x);
                    MOUSE_LEFT_DOWN = false;
                }
                if (button == GLFW_MOUSE_BUTTON_RIGHT && action == GLFW_PRESS) {
                    MOUSE_RIGHT_DOWN = true;
                }else if(button == GLFW_MOUSE_BUTTON_RIGHT && action == GLFW_RELEASE){
                    MOUSE_RIGHT_DOWN = false;
                }
            }
        });

        //Setup a cursor position callback
        glfwSetCursorPosCallback(window, cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                if(MOUSE_LEFT_DOWN){
                    CAMERA_ROT.x -= 0.5f * (MOUSE_LASTPOS.x - xpos);
                    CAMERA_ROT.y -= 0.5f * (MOUSE_LASTPOS.y - ypos);
                }
                if(MOUSE_RIGHT_DOWN){
                    CAMERA_POS.x += (CAMERA_FORWARD.x * ((float)(0.1f * (MOUSE_LASTPOS.y - ypos))));
                    CAMERA_POS.y += (CAMERA_FORWARD.y * ((float)(0.1f * (MOUSE_LASTPOS.y - ypos))));
                    //CAMERA_POS.y += 0.1f * (MOUSE_LASTPOS.x - xpos);
                    //CAMERA_POS.x += 0.1f * (MOUSE_LASTPOS.y - ypos);
                }


                MOUSE_LASTPOS.x = (float)xpos;
                MOUSE_LASTPOS.y = (float)ypos;
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center our window
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );

        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

    }

    private static void loop() {
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);

        // Set the clear color
        glClearColor(0.6f, 0.6f, 0.6f, 0.0f);

        //Load the default frag and vert shaders
        shaderProgram = new ShaderProgram("vertshader", "fragshader");

        //Get the location of uniform "color" in the shader
        int colorUniform = GL20.glGetUniformLocation(shaderProgram.getID(), "color");

        // Run the rendering loop until the user has attempted to close
        while ( glfwWindowShouldClose(window) == GLFW_FALSE ) {
            shaderProgram.bind();

            glViewport(0, 0, WIDTH, HEIGHT);

            FloatBuffer fb = BufferUtils.createFloatBuffer(16);
            projectionMatrix.setPerspective((float)Math.toRadians(90), WIDTH/HEIGHT, 0.1f, 100).get(fb);
            glMatrixMode(GL_PROJECTION);
            glLoadMatrixf(fb);

            viewMatrix.setLookAt(
                    15.0f, 0, .0f,
                    0, 0, 0,
                    0, 0, 1).get(fb);

            glMatrixMode(GL_MODELVIEW);
            glLoadMatrixf(fb);



            //Rotate the scene around Y and X axis to create an impression of orbit camera
            glRotatef(CAMERA_ROT.y, 0.0f, 1.0f, 0.0f);
            glRotatef(CAMERA_ROT.x, 0.0f, 0.0f, 1.0f);

            //Translate the scene to simulate panning
            glTranslatef(CAMERA_POS.x, CAMERA_POS.y, 0);

            //Clear the screen of previous draw calls
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            //Set the grid draw color to RED
            GL20.glUniform4f(colorUniform, 1.0f, 0.0f, 0.0f, 1.0f);
            drawFloorGrid();

            GL20.glUniform4f(colorUniform, 0.4f, 0.4f, 0.4f, 1.0f);

            for(int i = 0; i < CONTAINER_LATTICE.length; i++){
                for(int j = 0; j < CONTAINER_LATTICE[i].length; j++){
                    for(int k = 0; k < CONTAINER_LATTICE[i][j].length; k++){
                        if(CONTAINER_LATTICE[i][j][k] != 0){
                            //NOTE: Kamil's algorithm operates in a different spatial axis arrangement
                            //therefore Y and Z axles have to be swapped
                            drawCube(i, k, j);
                        }
                    }
                }

            }


            //Draw everything onto the screen
            glfwSwapBuffers(window);

            //Check for any events ( KB/mouse)
            glfwPollEvents();

        }
    }

}