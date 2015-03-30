package oyakov.model.type;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.glu.GLU;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public interface Renderable {
    void renderSelf(GL context, GLU glUtils);
}
