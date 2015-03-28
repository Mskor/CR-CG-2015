package oyakov.runtime;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;

public class RuntimeLauncher {

    private static Logger log;

    public static void main(String[] args) {
        ConfParmSubsystem confParmSubsystem = new ConfParmSubsystem();
        confParmSubsystem.initilaize();

        log = Logger.getLogger(RuntimeLauncher.class.getName());
        log.info("ConfParmLauncher has been initialized");

        GLCanvas canvas = new GLCanvas();
        canvas.setPreferredSize(
                new Dimension(
                        confParmSubsystem.getCtxt().getWIDTH(),
                        confParmSubsystem.getCtxt().getHEIGHT()
                )
        );
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.getContentPane().add(canvas);
            frame.addWindowListener(
                    new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            new Thread(() -> { System.exit(0); }).start();
                        }
                    }
            );
            frame.setTitle("Title");
            frame.pack();
            frame.setVisible(true);
        });

    }
}
