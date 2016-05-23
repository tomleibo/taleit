package model;

/**
 * Created by Kerzman on 5/17/2015
 */
public enum Categories {
    COMEDY("Comedy", "comedy"),
    DRAMA("Drama", "drama"),
    ADULTS("Adults", "adults"),
    SCIFI("Sci-Fi", "scifi"),
    HORROR("Horror", "horror"),
    ADVENTURE("Adventure", "adventure"),
    KIDS("Kids", "kids"),
    ROMANCE("Romance", "romance"),
    OTHERS("Others", "others");

    private final String value;
    private final String image;


    Categories(String value, String image) {
        final String base = "http://localhost:8080/resources/categories/";
        this.value = value;
        this.image = base+image;
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

    public String getImageUrl() {
        return this.image+".png";
    }

    public String getImageHoverUrl() {
        return this.image+"_hover.png";
    }

}
