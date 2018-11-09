package co.yosola.clubsandwich.Model;

import java.util.ArrayList;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public void setRecipeUrl(String recipeUrl) {
        this.recipeUrl = recipeUrl;
    }

    public static ArrayList<Sandwich> createSandwichList(int numJsonObjects){
        ArrayList<Sandwich> sandwichArrayList = new ArrayList<Sandwich>();
        for(int i = 0; i < numJsonObjects; i++){
            sandwichArrayList.add(new Sandwich());
        }

        return sandwichArrayList;
    }

}
