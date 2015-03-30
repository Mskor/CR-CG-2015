package oyakov.runtime;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import oyakov.controllers.GLController;
import oyakov.controllers.KeyboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class GLSubsystem {

    private JFrame frame;
    private FPSAnimator animator;
    private GLCanvas canvas;

    public void initialize() {
        canvas = new GLCanvas();
        canvas.setPreferredSize(
                new Dimension(
                        800,
                        600
                        )
        );
        canvas.addKeyListener(new KeyboardController());
        canvas.addGLEventListener(new GLController());

        animator = new FPSAnimator(canvas, 60, true);

        frame = new JFrame("TITLE");
        frame.getContentPane().add(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }.start();
            }
        });
    }

    public void activate() {
        frame.pack();
        frame.setVisible(true);

        animator.start();
    }
}
