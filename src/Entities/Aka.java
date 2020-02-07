/**
 * Aka.java
 *
 * @author Gautam Gadipudi
 *
 * @id gg7148
 */
package Entities;

public class Aka {
    private int titleId;
    private int orderNumber;
    private String titleName;
    private String region;
    private String language;
    private String types;
    private String attributes;
    private boolean isOriginalTitle;

    public Aka(){}

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public boolean isOriginalTitle() {
        return isOriginalTitle;
    }

    public void setOriginalTitle(boolean originalTitle) {
        isOriginalTitle = originalTitle;
    }
} //Aka
