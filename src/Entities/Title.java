package Entities;

public class Title {
    private int id;
    private String type;
    private String primaryTitleName;
    private String originalTitleName;
    private boolean isAdult;
    private int startYear;
    private int endYear;
    private int runtimeMinutes;
    private String genres;

    public Title(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimaryTitleName() {
        return primaryTitleName;
    }

    public void setPrimaryTitleName(String primaryTitleName) {
        this.primaryTitleName = primaryTitleName;
    }

    public String getOriginalTitleName() {
        return originalTitleName;
    }

    public void setOriginalTitleName(String originalTitleName) {
        this.originalTitleName = originalTitleName;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
