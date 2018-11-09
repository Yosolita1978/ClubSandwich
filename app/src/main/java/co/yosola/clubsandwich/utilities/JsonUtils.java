package co.yosola.clubsandwich.utilities;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.yosola.clubsandwich.Model.Sandwich;

public class JsonUtils {

    //Create all the variables in the Sandwich Json

    private static final String RECIPES = "recipes";
    private static final String TITLE = "title";
    private static final String PUBLISHER = "publisher";
    private static final String IMAGE_URL = "image_url";
    private static final String SOURCE_URL = "source_url";

    //For debugging reasons

    private static String TAG = JsonUtils.class.toString();

    //Parse the Json to create the new Sandwich object

    public static List<Sandwich> parseSandwichJson(String json) {

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding ranked articles to
        List<Sandwich> sandwichesList = new ArrayList<>();

        try {
            JSONObject jsonData = new JSONObject(json);

            JSONArray jsonArray = jsonData.optJSONArray(RECIPES);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject recipe = jsonArray.optJSONObject(i);
                String name = recipe.optString(TITLE);
                String publisher = recipe.optString(PUBLISHER);
                String imageUrl = recipe.optString(IMAGE_URL);
                String sourceUrl = recipe.optString(SOURCE_URL);

                sandwichesList.add(new Sandwich(name, publisher, imageUrl, sourceUrl));
            }


        } catch (JSONException e) {
            Log.d(TAG, "An error had occurred on JSON Parsing");
            e.printStackTrace();
        }

        return  sandwichesList;
    }

}
