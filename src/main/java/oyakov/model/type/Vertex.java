package oyakov.model.type;

/**
 * Created by oyakovlev on 30.03.2015.
 */
class Vertex {
    public float x, y, z,
            u, v;

    public Vertex() {}

    public Vertex(float x, float y, float z, float u, float v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
    }

    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]" + " [" + u + ", " + v + "]";
    }

}
