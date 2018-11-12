package co.yosola.clubsandwich;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.yosola.clubsandwich.Model.Sandwich;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.CustomViewHolder> {


    private OnItemClickListener onItemClickListener;

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
            Picasso.get().load(sandwichItem.getimageUrl())
                    .placeholder(R.drawable.sandwichdefault)
                    .into(customViewHolder.imageView);
        }

        //Setting text view title
        customViewHolder.textView.setText(sandwichItem.getName());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(sandwichItem);
            }
        };
        customViewHolder.imageView.setOnClickListener(listener);
        customViewHolder.textView.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return (null != mSandwichList ? mSandwichList.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.sandwich_image);
            this.textView = (TextView) view.findViewById(R.id.sandwich_name);
        }

    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
