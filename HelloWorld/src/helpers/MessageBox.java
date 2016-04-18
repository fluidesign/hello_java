package helpers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;

public class MessageBox {

	public static void info(String message) {
        info(message, whoCalledMe());
    }
    public static void info(String message, String caller) {
        show(message, caller, JOptionPane.INFORMATION_MESSAGE);
    }

    static void error(String message) {
        error(message, whoCalledMe());
    }
    public static void error(String message, String caller) {
        show(message, caller, JOptionPane.ERROR_MESSAGE);
    }

    public static void show(String message, String title, int iconId) {
        setClipboard(title+":"+NEW_LINE+message);
        JOptionPane.showMessageDialog(null, message, title, iconId);
    }
    private static final String NEW_LINE = System.lineSeparator();

    public static String whoCalledMe() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    public static void setClipboard(String message) {
        CLIPBOARD.setContents(new StringSelection(message), null);
    }
    private static final Toolkit AWT_TOOLKIT = Toolkit.getDefaultToolkit();
    private static final Clipboard CLIPBOARD = AWT_TOOLKIT.getSystemClipboard();    
}
