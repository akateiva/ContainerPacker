package UI;

import algorithms.KnapAlg;
import algorithms.KnapAlgPent;

import org.lwjgl.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.joml.*;

import java.nio.FloatBuffer;
import java.util.Hashtable;

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
    static private int CONTAINER_WIDTH = 5;
    static private int CONTAINER_HEIGHT = 8;
    static private int CONTAINER_LENGTH = 33;
    static private int[][][] CONTAINER_LATTICE = {};

    //Camera information
    static private Vector3f CAMERA_POS = new Vector3f(0,0,0); //Coordinates of the camera view target
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

    //Hacks and unelegant workarounds for OS X compatibility
    static boolean PARAM_NO_UI = false;
    static boolean PARAM_NO_GLFW = false;
    static boolean PARAM_SCALEFIX = false;

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        Hashtable algParameters = new Hashtable();
        try {

            //Iterate through the command-line arguments
            for(int i = 0; i < args.length; i++){
                switch(args[i].toLowerCase()){
                    //The SCALEFIX parameter fixes viewport scaling issue for Retina screens
                    case "-scalefix":
                        PARAM_SCALEFIX = true;
                        break;
                    //The -no-swing parameter will start the application without the Swing dialog
                    //(because Swing and GLFW doesn't work together on OS X)
                    case "-no-swing":
                        PARAM_NO_UI = true;
                        break;
                    //Start the program without a GLFW window
                    case "-no-glfw":
                        PARAM_NO_GLFW = true;
                        break;
                    //Sets an algorithm parameter ( will automatically start an algorithm and skip the Swing dialog )
                    case "-a":
                        if(i < args.length - 2){
                            algParameters.put(args[i+1].toLowerCase(), args[i+2].toLowerCase());
                            i+=2;
                        }
                        break;
                    default:
                        System.out.printf("Unknown parameter %s !!!\n", args[i]);
                }
            }
            if(PARAM_NO_UI) {
                startAlgorithm(algParameters);
            }else{
                initSwing();
                if(algParameters.size() > 0)
                    System.out.println("Warning : Algorithm parameters ( -a ) were set but will be ignored, because the program was started with the UI. If you want to launch an algorithm directly please add -no-swing program parameter!");
            }

            if(PARAM_NO_GLFW == false) {
                init();
                loop();
                glfwDestroyWindow(window);
                keyCallback.release();
                mouseCallback.release();
            }
        } finally {
            if(PARAM_NO_GLFW == false) {
                glfwTerminate();
                errorCallback.release();
            }
        }
    }

    public static void DEBUG_PRINT_ALG_PARAMETERS(Hashtable algParameters){
        System.out.println(algParameters.toString());
    }
    public static void startAlgorithm(Hashtable algParameters){
        int POPULATION_SIZE = Integer.parseInt(algParameters.getOrDefault("population_size", "100").toString());
        int MUTATION_RATE = Integer.parseInt(algParameters.getOrDefault("mutation_rate", "1").toString());
        int THRESHOLD = Integer.parseInt(algParameters.getOrDefault("threshold", "85").toString());

        if(algParameters.containsKey("pentomino")){
            new Thread(new KnapAlgPent(POPULATION_SIZE, MUTATION_RATE, THRESHOLD)).start();
        }else{
            new Thread(new KnapAlg(POPULATION_SIZE, MUTATION_RATE, THRESHOLD)).start();
        }
    }

    /**
     * Store a lattice (int[][]) that will be drawn on screen
     * @param lattice
     */
    public static void setLattice(int[][][] lattice){
        CONTAINER_LATTICE = lattice;
    }

    /**
     * Draw a cube on the screen at a position.
     * @param x
     * @param y
     * @param z
     */
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

    /**
     * Draw the floor grid
     */
    private static void drawFloorGrid(){
        glBegin(GL_LINES);
        for(int i=-20;i<=20;i++) {
            glVertex3f(i,-20,0);
            glVertex3f(i,20,0);
            glVertex3f(-20,i,0);
            glVertex3f(20,i,0);
        };
        glEnd();
    }

    /**
     * Start the Swing UI
     */
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

    /**
     * Initialize GLFW
     */
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

    /**
     * Window3DView thread loop
     */
    private static void loop() {
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);

        // Set the clear color
        glClearColor(0.6f, 0.6f, 0.6f, 0.0f);

        //Load the default frag and vert shaders
        shaderProgram = new ShaderProgram("UI/vertshader", "UI/fragshader");

        //Get the location of uniform "color" in the shader
        int colorUniform = GL20.glGetUniformLocation(shaderProgram.getID(), "color");

        // Run the rendering loop until the user has attempted to close
        while ( glfwWindowShouldClose(window) == GLFW_FALSE ) {
            shaderProgram.bind();

            if(PARAM_SCALEFIX){
                glViewport(0, 0, WIDTH*2, HEIGHT*2);
            }else{
                glViewport(0, 0, WIDTH, HEIGHT);
            }


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
                            drawCube(i - (CONTAINER_WIDTH/2), k - (CONTAINER_LENGTH/2), j);
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