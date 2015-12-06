package edu.sjsu.qi.onlinecommunity;

/**
 * Created by qi on 12/5/15.
 */
public class UserItem {
    private int id;
    private String userName;
    private String Email;
    private String description;
    private String thumbnailURL;
    private String BigImageURL;
    private String IntroVideoRUL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getBigImageURL() {
        return BigImageURL;
    }

    public void setBigImageURL(String bigImageURL) {
        BigImageURL = bigImageURL;
    }

    public String getIntroVideoRUL() {
        return IntroVideoRUL;
    }

    public void setIntroVideoRUL(String introVideoRUL) {
        IntroVideoRUL = introVideoRUL;
    }
}
