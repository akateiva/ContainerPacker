/**
 * Created by akateiva on 06/01/16.
 */
import java.nio.file.Files;
import java.nio.file.*;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private int programID;
    private int vertID;
    private int fragID;

    ShaderProgram(String vertPath, String fragPath){
        programID = glCreateProgram();
        String vertSource = "attribute vec3 vertexPosition;\n" +
                "void main(){\n" +
                "    gl_Position = gl_ModelViewProjectionMatrix * vec4(vertexPosition.x, vertexPosition.y, vertexPosition.z, 1.0);\n" +
                "}\n";
        String fragSource = "uniform vec4 color;\n" +
                "void main() {\n" +
                "gl_FragColor = color;\n" +
                "}";
        try {
            //vertSource = Files.lines(Paths.get(vertPath)).toString();
            //fragSource = Files.lines(Paths.get(fragPath)).toString();
        }
        catch(Exception e){
            System.out.println(e);
        }
        vertID = glCreateShader(GL_VERTEX_SHADER);
        fragID = glCreateShader(GL_FRAGMENT_SHADER);


        glShaderSource(vertID, vertSource);
        glShaderSource(fragID, fragSource);

        glCompileShader(vertID);
        glCompileShader(fragID);
        System.out.println(vertSource);

        glAttachShader(programID, vertID);
        glAttachShader(programID, fragID);

        glLinkProgram(programID);
    }

    public int getID(){
        return programID;
    }
    public void bind(){
        glUseProgram(programID);
    }

}