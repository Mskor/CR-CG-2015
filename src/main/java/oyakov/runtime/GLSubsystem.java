package oyakov.runtime;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import oyakov.controllers.GLController;
import oyakov.controllers.KeyboardController;
import oyakov.model.type.Entity;
import oyakov.model.type.FSEntityLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class GLSubsystem {

    private static GLSubsystem instance;

    public static GLSubsystem getInstance() {
        if(instance == null) {
            instance = new GLSubsystem();
        }
        return instance;
    }

    private GLSubsystem() {}

    private JFrame frame;
    private FPSAnimator animator;

    private FSEntityLoader fsEntityLoader;

    private Map<String, Entity> entityCache;

    private static Logger log = Logger.getLogger(GLSubsystem.class.getName());

    public void initialize() {
        GLCanvas canvas = new GLCanvas();
        canvas.setPreferredSize(
                new Dimension(
                        800,
                        600
                )
        );
        canvas.addKeyListener(new KeyboardController());
        canvas.addGLEventListener(new GLController());

        animator = new FPSAnimator(canvas, 60, true);

        frame = new JFrame(ConfParmSubsystem.getInstance().getCtxt().getTITLE());
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

        fsEntityLoader = new FSEntityLoader();
        entityCache = new HashMap<>();
    }

    public void activate() {
        frame.pack();
        frame.setVisible(true);

        animator.start();
    }

    public Entity getEntity(String key) {
        if(entityCache.containsKey(key)) {
            return entityCache.get(key);
        } else {
            try{
                Entity e = fsEntityLoader.loadEntity(key);
                entityCache.put(key, e);
                return e;
            } catch (IOException ioe) {
                log.info("Failed to load entity...");
                return null;
            }
        }
    }
}
