package co.yosola.clubsandwich;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.yosola.clubsandwich.Model.Sandwich;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private ArrayList<Sandwich> mSandwichList;

    private TextView mSandwichName;

    private ImageView mSandwichImage;

    private TextView mSandwichPublisher;

    public static final String EXTRA_POSITION = "extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: started.");


    }
}
