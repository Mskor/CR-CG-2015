package oyakov.controllers;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import oyakov.model.type.Entity;
import oyakov.runtime.GLSubsystem;

import static com.jogamp.opengl.GL.*;  // GL constants
import static com.jogamp.opengl.GL2.*; // GL2 constants

/**
 * Created by oyakovlev on 28.03.2015.
 */
public class GLController implements GLEventListener{

    GLU glUtils;

    /**
     * Called back immediately after the OpenGL context is initialized. Can be used
     * to perform one-time initialization. Run only once.
     */
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 glContext = glAutoDrawable.getGL().getGL2();
        glUtils = new GLU();
        glContext.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glContext.glClearDepth(1.0f);      // set clear depth value to farthest
        glContext.glEnable(GL_DEPTH_TEST); // enables depth testing
        glContext.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
        glContext.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
        glContext.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
    }

    /**
     * Called back by the animator to perform rendering.
     */
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        Entity e = GLSubsystem.getInstance().getEntity("geometry");
        e.renderSelf(gl, glUtils);
    }

    /**
     * Call-back handler for window re-size event. Also called when the drawable is
     * first set to visible.
     */
    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL2 glContext = glAutoDrawable.getGL().getGL2();

        if(height == 0) {
            height = 1;
        }

        float aspect = (float) width / height;

        // Set the view port (display area) to cover the entire window
        glContext.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        glContext.glMatrixMode(GL_PROJECTION);  // choose projection matrix
        glContext.glLoadIdentity();             // reset projection matrix
        glUtils.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

        // Enable the model-view transform
        glContext.glMatrixMode(GL_MODELVIEW);
        glContext.glLoadIdentity(); // reset
    }

    /**
     * Called back before the OpenGL context is destroyed. Release resource such as buffers.
     */
    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }
}
