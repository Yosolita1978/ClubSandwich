package co.yosola.clubsandwich;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

import co.yosola.clubsandwich.Model.Sandwich;
import co.yosola.clubsandwich.utilities.JsonUtils;
import co.yosola.clubsandwich.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;

    private SandwichAdapter mSandwichAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    private ArrayList<Sandwich> mSandwichList;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
        mErrorMessageDisplay = (TextView) findViewById(R.id.error_message_display);

        new DownloadTask().execute();
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


    public class DownloadTask extends AsyncTask<String, Void, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }


        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            URL foodRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonResponse = NetworkUtils
                        .getResponseFromHttpUrl(foodRequestUrl);

                mSandwichList = JsonUtils
                        .parseSandwichJson(MainActivity.this, jsonResponse);

                result = 1; // Successful

            } catch (Exception e) {
                e.printStackTrace();
                result = 0; //"Failed to fetch data!";
            }

            return  result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (result == 1) {
                showJsonDataView();
                mSandwichAdapter = new SandwichAdapter(MainActivity.this, mSandwichList);
                mRecyclerView.setAdapter(mSandwichAdapter);
                mSandwichAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Sandwich sandwich) {
                        Toast.makeText(MainActivity.this, sandwich.getName(), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("sandwich_name", sandwich.getName());
                        startActivity(intent);

                    }
                });

            } else {
                showErrorMessage();
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
