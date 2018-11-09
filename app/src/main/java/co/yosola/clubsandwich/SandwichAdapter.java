package co.yosola.clubsandwich;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.yosola.clubsandwich.Model.Sandwich;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.CustomViewHolder> {

    private ArrayList<Sandwich> mSandwichList;
    private Context mContext;


    public SandwichAdapter(Context context, ArrayList<Sandwich> sandwiches) {
        this.mSandwichList = sandwiches;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final Sandwich sandwichItem = mSandwichList.get(i);

        //Download image using picasso library
        if (!TextUtils.isEmpty(sandwichItem.getimageUrl())) {
            Picasso.with(mContext).load(sandwichItem.getimageUrl())
                    .error(R.drawable.sandwichdefault)
                    .placeholder(R.drawable.sandwichdefault)
                    .into(customViewHolder.imageView);
        }

        //Setting text view title
        customViewHolder.textView.setText(sandwichItem.getName());

    }

    @Override
    public int getItemCount() {
        return (null != mSandwichList ? mSandwichList.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.sandwich_image);
            this.textView = (TextView) view.findViewById(R.id.sandwich_name);
        }
    }

}
