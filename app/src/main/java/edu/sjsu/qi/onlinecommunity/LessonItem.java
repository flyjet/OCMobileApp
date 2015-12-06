package edu.sjsu.qi.onlinecommunity;

/**
 * Created by qi on 12/6/15.
 */
public class LessonItem {
    private int id;
    private String lessonName;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
