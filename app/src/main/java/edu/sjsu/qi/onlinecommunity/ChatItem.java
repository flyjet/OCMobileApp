package edu.sjsu.qi.onlinecommunity;

/**
 * Created by qi on 12/7/15.
 */
public class ChatItem {
    private String UserName;
    private String message;
    private long timeStamp;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


}
