package co.yosola.clubsandwich.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import co.yosola.clubsandwich.BuildConfig;

public class NetworkUtils {

    // A method to storage my api key private. See the build.gradle for more details.
    private static final String apiKey = BuildConfig.ApiKey;

    final static String GITHUB_BASE_URL =
            "https://www.food2fork.com/api/search";

    final static String PARAM_QUERY = "q";

    final static String PARAM_COUNT = "count";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter("key", apiKey)
                .appendQueryParameter(PARAM_QUERY, "Sandwich")
                .appendQueryParameter(PARAM_COUNT, "10")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
