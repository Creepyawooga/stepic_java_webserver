package accountServer;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class AccountServer extends NotificationBroadcasterSupport implements AccountServerI, AccountServerMBean {
    private int usersCount;
    private int usersLimit;
    private long sequenceNumber = 1;

    public AccountServer(int usersLimit) {
        this.usersCount = 0;
        this.usersLimit = usersLimit;
    }

    @Override
    public void addNewUser() {
        usersCount += 1;
    }

    @Override
    public void removeUser() {
        usersCount -= 1;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
        Notification notification = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(),
                "Users limit changed", "Users limit", "int", this.usersLimit, usersLimit);
        sendNotification(notification);
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] {AttributeChangeNotification.ATTRIBUTE_CHANGE};
        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[] {info};
    }
}
