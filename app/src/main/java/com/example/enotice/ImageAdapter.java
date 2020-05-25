package com.example.enotice;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
        private Context mContext;
        private List<Upload> mUploads;
        private OnItemClickListener mlistener;

        public ImageAdapter(Context context, List<Upload> uploads) {
            mContext = context;
            mUploads = uploads;
        }

    public Context getmContext() {
        return mContext;
    }

    @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.file_item, parent, false);
            return new ImageViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            Upload uploadCurrent = mUploads.get(position);
                holder.title.setText(uploadCurrent.getEtext()+" Department");
               holder.des.setText(uploadCurrent.getTitle());
                holder.datetime.setText(uploadCurrent.getDate());


               /* Picasso.with(mContext)
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

        public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
            public TextView title;
            public ImageView image;
            public TextView des;
            public TextView datetime;
            public ImageViewHolder(View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                image = itemView.findViewById(R.id.image);
                des=itemView.findViewById(R.id.des);
                datetime=itemView.findViewById(R.id.datetime);
                itemView.setOnClickListener(this);
                itemView.setOnCreateContextMenuListener(this);
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

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("select Action");
                MenuItem delete=menu.add(Menu.NONE,1,1,"Delete");
                delete.setOnMenuItemClickListener(this);
            }

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(mlistener!=null){
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                      switch (item.getItemId()){
                          case 1:
                          mlistener.onDeleteClick(position);
                          return true;

                      }
                    }
                }
                return false;
            }
        }
        public interface OnItemClickListener{
            void onItemClick(int position);
            void onDeleteClick(int position);
        }
        public void setOnItemClickListener(OnItemClickListener listener){
           mlistener=listener;
        }

    }

