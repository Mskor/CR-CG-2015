package oyakov.runtime;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ConfParmSubsystem {

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

    AppContext getCtxt() {
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
        }

        static protected AppContext loadContext() {
            Properties props = new Properties();
            try{
                props.load(ConfParmSubsystem.class.getResourceAsStream("/appcontext.properties"));
            } catch (IOException ioe) {
                log.info("Couldn't load application context: \n" + ioe.toString());
            }

            return new AppContext(props);
        }

        private int WIDTH;
        private final int DEFAULT_WIDTH = 800;

        public int getWIDTH() {
            return WIDTH;
        }

        private int HEIGHT;
        private final int DEFAULT_HEIGHT = 600;

        public int getHEIGHT() {
            return HEIGHT;
        }
    }
}
