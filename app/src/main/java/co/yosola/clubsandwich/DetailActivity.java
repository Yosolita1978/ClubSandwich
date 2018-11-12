package co.yosola.clubsandwich;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.yosola.clubsandwich.Model.Sandwich;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private TextView mSandwichName;

    private ImageView mSandwichImage;

    private TextView mSandwichPublisher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();

    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String sandwichName_temp = extras.getString("sandwich_name");
        String sandwichPublisher_temp = extras.getString("Sandwich_publisher");
        String sandwichUrl_temp = extras.getString("Sandwich_url");
        String sandwichImage_temp = extras.getString("Sandwich_img_url");

        if(sandwichName_temp != null && sandwichPublisher_temp != null && sandwichUrl_temp != null && sandwichImage_temp != null){
            Log.d(TAG, "getIncomingIntent: " + sandwichName_temp + sandwichPublisher_temp + sandwichUrl_temp + sandwichImage_temp);

            Sandwich newSandwich = new Sandwich();
            newSandwich.setName(sandwichName_temp);
            newSandwich.setPublisher(sandwichPublisher_temp);
            newSandwich.setRecipeUrl(sandwichUrl_temp);
            newSandwich.setImageUrl(sandwichImage_temp);

            setUI(newSandwich);

        } else {
            Log.d(TAG, "Something went  wrong with the intent");

            setUI(new Sandwich());
        }

    }

    private void setUI(Sandwich sandwich){
        Log.d(TAG, "setUI: setting the UI with the current Sandwich.");

        mSandwichName = findViewById(R.id.sandwich_name_detail);
        mSandwichName.setText(sandwich.getName());

        mSandwichPublisher = findViewById(R.id.sandwich_publisher);
        mSandwichPublisher.setText(sandwich.getPublisher());

        mSandwichImage = findViewById(R.id.image_sandwich);
        String sandwichImageURl = sandwich.getimageUrl();
        Picasso.get().load(sandwichImageURl)
                .placeholder(R.drawable.sandwichdefault)
                .into(mSandwichImage);

        //SetUp the Recipe Button
        TextView recipeSandwich = findViewById(R.id.sandwich_source);
        final String currentRecipe = sandwich.getRecipeUrl();

        // Invoke url upon button click
        recipeSandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(currentRecipe));
                startActivity(intent);
            }
        });

    }

}
