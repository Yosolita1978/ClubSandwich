package co.yosola.clubsandwich;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;

    private SandwichAdapter mSandwichAdapter;


    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    private List<Sandwich> mSandwichList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        mErrorMessageDisplay = (TextView) findViewById(R.id.error_message_display);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mSandwichAdapter = new SandwichAdapter();

        mRecyclerView.setAdapter(mSandwichAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        makeFoodSearchQuery();
    }

    private void makeFoodSearchQuery() {
        showJsonDataView();
        URL foodSearchUrl = NetworkUtils.buildUrl();
        new FoodQueryTask().execute();
    }

    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        // First, hide the currently visible data
        mRecyclerView.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }



    public class FoodQueryTask extends AsyncTask<String, Void, String[]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {

            URL foodRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonResponse = NetworkUtils
                        .getResponseFromHttpUrl(foodRequestUrl);

                String[] simpleJsonData = JsonUtils
                        .parseSandwichJson(MainActivity.this, jsonResponse);

                return simpleJsonData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] sandwichData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (sandwichData != null) {
                showJsonDataView();
                mSandwichAdapter.setSandwichData(sandwichData);
            } else {
                showErrorMessage();
            }
        }
    }
}
