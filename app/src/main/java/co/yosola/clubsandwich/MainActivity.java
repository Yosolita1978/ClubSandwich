package co.yosola.clubsandwich;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import co.yosola.clubsandwich.Model.Sandwich;
import co.yosola.clubsandwich.utilities.JsonUtils;
import co.yosola.clubsandwich.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private TextView mSearchResultsTextView;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    private List<Sandwich> mSandwichList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchResultsTextView = (TextView) findViewById(R.id.sandwiches_show);

        // COMPLETED (13) Get a reference to the error TextView using findViewById
        mErrorMessageDisplay = (TextView) findViewById(R.id.error_message_display);

        // COMPLETED (25) Get a reference to the ProgressBar using findViewById
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        makeFoodSearchQuery();
    }

    private void makeFoodSearchQuery() {
        URL foodSearchUrl = NetworkUtils.buildUrl();
        mSearchResultsTextView.setText(foodSearchUrl.toString());
        new FoodQueryTask().execute(foodSearchUrl);
    }

    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        // First, hide the currently visible data
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FoodQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                mSandwichList = JsonUtils.parseSandwichJson(githubSearchResults);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mSandwichList.toString();
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null && !githubSearchResults.equals("")) {

                showJsonDataView();
                mSearchResultsTextView.setText(githubSearchResults);
            } else {

                showErrorMessage();
            }
        }
    }

}
