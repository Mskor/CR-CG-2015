package oyakov.model.type;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by oyakovlev on 31.03.2015.
 */
public class Entity implements Renderable {

    private List<Renderable> geometry;

    public Entity() {}

    public Entity(List<Renderable> geometry) {
        this.geometry = geometry;
    }

    @Override
    public void renderSelf(GL2 context, GLU glUtils) {
        geometry.forEach(e -> e.renderSelf(context, glUtils));
    }

    @Override
    public Renderable mirrorX() {

        List<Renderable> reflection = geometry.stream().map(Renderable::mirrorX).collect(Collectors.toList());

        return new Entity(reflection);
    }
}
