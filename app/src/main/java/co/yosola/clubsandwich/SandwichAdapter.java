package co.yosola.clubsandwich;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.yosola.clubsandwich.Model.Sandwich;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichAdapterViewHolder> {

    private String[] mSandwichData;
    private Context mContext;

    public SandwichAdapter() {

    }


    @NonNull
    @Override
    public SandwichAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new SandwichAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SandwichAdapterViewHolder holder, int position) {
        String [] sandwichNames = mContext.getResources().getStringArray(R.array.sandwich_names);

        String [] imageUrls = mContext.getResources().getStringArray(R.array.sandwich_urls);

        Picasso.with(mContext)
                .load(imageUrls[position])
                .into(holder.mSandwichImageView);

        holder.mSandwichTextView.setText(sandwichNames[position]);

    }

    @Override
    public int getItemCount() {
        if (null == mSandwichData) return 0;
        return mSandwichData.length;
    }

    public class SandwichAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mSandwichTextView;

        public final ImageView mSandwichImageView;

        public SandwichAdapterViewHolder(View view) {
            super(view);
            mSandwichTextView = (TextView) view.findViewById(R.id.sandwich_name);
            mSandwichImageView = (ImageView) view.findViewById(R.id.sandwich_image);
        }
    }

    public void setSandwichData(String[] sandwichesData) {
        mSandwichData = sandwichesData;
        notifyDataSetChanged();
    }
}
