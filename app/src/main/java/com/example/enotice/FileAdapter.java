package com.example.enotice;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;
    private FileAdapter.OnItemClickListener mlistener;

    public FileAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public FileAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.file_item, parent, false);
        return new FileAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FileAdapter.ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);

        holder.title.setText(uploadCurrent.getTitle());
        holder.des.setText(uploadCurrent.getDes());
        holder.datetime.setText(uploadCurrent.getDate());
        /*Picasso.with(mContext)
                .load(uploadCurrent.getMimageurl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.image);*/
        // Glide.with(mContext).load(uploadCurrent.getMimageurl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public ImageView image;
        public TextView des,datetime;

        public ImageViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            des=itemView.findViewById(R.id.des);
            datetime=itemView.findViewById(R.id.datetime);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            if(mlistener!=null){
                int position=getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    mlistener.onItemClick(position);
                }
            }
        }

    }
    public interface OnItemClickListener{
        void onItemClick(int position);

    }
    public void setOnItemClickListener(FileAdapter.OnItemClickListener listener){
        mlistener=listener;
    }
}
