package mwx.demo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mwx.demo.Models.DataModel;

import mwx.demo.R;
import mwx.textoimage.TextoImage;
import mwx.textoimage.AsyncResponse;
import mwx.textoimage.Type;

/**
 * Created by Nauman on 6/20/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements AsyncResponse {

    private  ArrayList<DataModel> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
private Context context;
    ViewHolder viewHolder;
    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<DataModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context=context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
          viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.myTextView.setText(mData.get(position).getTitle());
         holder.myId.setText(mData.get(position).getId());
holder.linearLayout.setBackgroundColor(Color.parseColor(mData.get(position).getColor()));
 //pass image view in the constructor if it an image job
        TextoImage textoImage = new TextoImage(context, Type.IMAGE, mData.get(position).getThumb(),holder.myImageView);
        textoImage.delegate = MyRecyclerViewAdapter.this;
        textoImage.execute();
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void dataLoaded(String output) {

    }




    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myTextView,myId;
        public ImageView myImageView;
public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.tvTitle);
            myImageView = (ImageView) itemView.findViewById(R.id.ivThumb);
            myId = (TextView) itemView.findViewById(R.id.tvId);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.topLayout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
