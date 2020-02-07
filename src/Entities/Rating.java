/**
 * Rating.java
 *
 * @author Gautam Gadipudi
 *
 * @id gg7148
 */
package Entities;

public class Rating {
    private int titleId;
    private float averageRating;
    private int voteCount;

    public Rating(){}

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
} //Rating
