package edu.sjsu.qi.onlinecommunity;

/**
 * Created by qi on 11/4/15.
 */
public class CourseItem {

    private int id;
    private String courseName;
    private String description;
    private float rating;
    private String instructorName;
    private String thumbnailURL;
    private String BigImageURL;
    private String IntroVideoRUL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
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
