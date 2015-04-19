package oyakov.runtime;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ConfParmSubsystem {

    private static ConfParmSubsystem instance;

    public static ConfParmSubsystem getInstance() {
        if(instance == null) {
            instance = new ConfParmSubsystem();
        }
        return instance;
    }

    private ConfParmSubsystem() {}

    private AppContext ctxt;
    private static Logger log;

    public void initialize() {

        // Init logging first
        try {
            LogManager.getLogManager().readConfiguration(
                    RuntimeLauncher.class.getResourceAsStream("/logging.properties")
            );
        } catch (IOException ioe) {
            System.err.println("WARNING: Couldn't read logging configuration!");
        }

        // Since logging is initialized, we can use it from now on...
        log = Logger.getLogger(ConfParmSubsystem.class.getName());
        log.info("Logging config successfully loaded...");

        ctxt = AppContext.loadContext();
    }

    public AppContext getCtxt() {
        return ctxt;
    }

    public static class AppContext {
        private AppContext(Properties props) {
            String currentProperty;

            currentProperty = props.getProperty("initialWidth");
            if(currentProperty != null) {
                WIDTH = Integer.parseInt(currentProperty);
            } else {
                WIDTH = DEFAULT_WIDTH;
            }

            currentProperty = props.getProperty("initialHeight");
            if(currentProperty != null) {
                HEIGHT = Integer.parseInt(currentProperty);
            } else {
                HEIGHT = DEFAULT_HEIGHT;
            }

            currentProperty = props.getProperty("title");
            if(currentProperty != null) {
                TITLE = currentProperty;
            } else {
                TITLE = DEFAULT_TITLE;
            }
        }

        static AppContext loadContext() {
            Properties props = new Properties();
            try{
                props.load(ConfParmSubsystem.class.getResourceAsStream("/appcontext.properties"));
            } catch (IOException ioe) {
                log.info("Couldn't load application context: \n" + ioe.toString());
            }

            return new AppContext(props);
        }

        private final int WIDTH;
        private final int DEFAULT_WIDTH = 800;

        public int getWIDTH() {
            return WIDTH;
        }

        private final int HEIGHT;
        private final int DEFAULT_HEIGHT = 600;

        public int getHEIGHT() {
            return HEIGHT;
        }

        private final String TITLE;
        private final String DEFAULT_TITLE = "Title didn't get loaded...";

        public String getTITLE() {
            return TITLE;
        }

        public float cameraAngleX=0.0f, cameraAngleY=0.0f, cameraAngleZ=0.0f;

        public float cameraOffsetX=0.0f, cameraOffsetY=0.0f, cameraOffsetZ=-6.0f;

        public float leftWingAngle=-30.0f, rightWingAngle=90.0f;
    }
}
