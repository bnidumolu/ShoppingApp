import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("ShoppingSystem");

    static {
        try {
            FileHandler fh = new FileHandler("shopping_app.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        logger.info(message);
    }
}
