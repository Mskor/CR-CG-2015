package oyakov.model.type;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public interface Renderable {
    void renderSelf(GL2 context, GLU glUtils);
}
