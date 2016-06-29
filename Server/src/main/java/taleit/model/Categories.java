package taleit.model;

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
    COMICS("Comics", "comics"),
    ROMANCE("Romance", "romance"),
    OTHERS("Others", "other");

    private final String value;
    private final String image;

    Categories(String value, String image) {
        final String base = "/resources/categories/";
        this.value = value;
        this.image = base+image;
    }

    static public Categories getCategoryByString(String categoryStr) {
        try {
            for (Categories category : Categories.values()) {
                if (category.getValue().toLowerCase().equals(categoryStr.toLowerCase())) {
                    return category;
                }
            }
        }catch (Exception ex) {}
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
