package edu.sjsu.qi.onlinecommunity;

/**
 * Created by qi on 12/4/15.
 */
public class InstructorItem {
    private int id;
    private String name;
    private String school;
    private Float rating;
    private String thumbnailURL;
    private String description;

    private String BigImageURL;
    private String IntroVideoRUL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
