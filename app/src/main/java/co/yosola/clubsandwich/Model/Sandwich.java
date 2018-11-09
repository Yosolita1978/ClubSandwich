package co.yosola.clubsandwich.Model;

import java.util.List;

public class Sandwich {
    private String name;
    private String publisher;
    private String imageUrl;
    private String recipeUrl;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String name, String publisher, String imageUrl, String recipeUrl) {
        this.name = name;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
        this.recipeUrl = recipeUrl;
    }


    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

}
