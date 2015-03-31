package oyakov.model.type;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import java.util.List;

/**
 * Created by oyakovlev on 31.03.2015.
 */
public class Entity implements Renderable {

    private List<Triangle> geometry;

    public Entity() {}

    public Entity(List<Triangle> geometry) {
        this.geometry = geometry;
    }

    @Override
    public void renderSelf(GL2 context, GLU glUtils) {
        geometry.forEach(e -> e.renderSelf(context, glUtils));
    }
}
