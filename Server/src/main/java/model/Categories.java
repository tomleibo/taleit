package model;

/**
 * Created by Kerzman on 5/17/2015
 */
public enum Categories {
    COMEDY("Comedy"),
    DRAMA("Drama"),
    ADULTS("Adults"),
    SCIFI("Sci-Fi"),
    HORROR("Horror"),
    ADVENTURE("Adventure"),
    KIDS("Kids"),
    ROMANCE("Romance"),
    OTHERS("Others");

    private final String value;

    Categories(String value) {
        this.value = value;
    }

    static public Categories getCategoryByString(String categoryStr) {
        for (Categories category : Categories.values()) {
            if (category.getValue().toLowerCase().equals(categoryStr.toLowerCase())) {
                return category;
            }
        }
        return Categories.OTHERS;
    }

    public String getValue() {
        return this.value;
    }

}
