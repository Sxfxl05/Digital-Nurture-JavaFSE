import com.patterns.singleton.Logger;
import com.patterns.factory.NotificationFactory;
import com.patterns.factory.Notification;
public class Week1Main {
    public static void main(String[] args) {
        System.out.println("=== WEEK 1: DESIGN PATTERNS TEST ===\n");
        Logger logger1 = Logger.getInstance();
        logger1.log("Testing pattern mechanics.");
        NotificationFactory factory = new NotificationFactory();
        Notification sms = factory.createNotification("SMS");
        if (sms != null) sms.notifyUser();
    }
}
