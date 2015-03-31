package oyakov.model.type;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class Triangle implements Renderable {

    private final Vertex[] vertices = new Vertex[3];

    public Triangle(Vertex v0, Vertex v1, Vertex v2) {
        vertices[0] = v0;
        vertices[1] = v1;
        vertices[2] = v2;
    }

    @Override
    public void renderSelf(GL2 context, GLU glUtils) {
        context.glBegin(GL.GL_TRIANGLES);
            context.glVertex3f(vertices[0].x, vertices[0].y, vertices[0].z);
            context.glVertex3f(vertices[1].x, vertices[1].y, vertices[1].z);
            context.glVertex3f(vertices[2].x, vertices[2].y, vertices[2].z);
        context.glEnd();
    }

    public String toString() {

        StringBuilder strb = new StringBuilder();
        strb.append(vertices[0]);
        strb.append(System.getProperty("line.separator"));
        strb.append(vertices[0]);
        strb.append(System.getProperty("line.separator"));
        strb.append(vertices[0]);

        return strb.toString();
    }
}
