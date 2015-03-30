package oyakov.model.type;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.glu.GLU;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class Triangle implements Renderable {

    Vertex[] vertices = new Vertex[3];

    public Triangle() {
        vertices[0] = new Vertex();
        vertices[1] = new Vertex();
        vertices[2] = new Vertex();
    }

    public static Triangle fromString(String source) {
        //TODO: implement this
        return new Triangle();
    }

    @Override
    public void renderSelf(GL context, GLU glUtils) {

    }

    public String toString() {
        return vertices[0].toString() + "\n" +
                vertices[1].toString() + "\n" +
                  vertices[2].toString();
    }
}
