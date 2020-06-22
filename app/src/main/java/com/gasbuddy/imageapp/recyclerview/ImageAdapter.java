package com.gasbuddy.imageapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gasbuddy.imageapp.R;
import com.gasbuddy.imageapp.models.ImageMetadata;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

      private List<ImageMetadata> listOfImages;
      private RecyclerItemClickListener recyclerItemClickListener;

      public ImageAdapter(List<ImageMetadata> listOfImages, RecyclerItemClickListener recyclerItemClickListener){
          this.listOfImages = listOfImages;
          this.recyclerItemClickListener = recyclerItemClickListener;
      }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        final ImageMetadata currentImage = listOfImages.get(position);
        Picasso.get().load(currentImage.getImage_url()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> recyclerItemClickListener.onItemClick(currentImage));

      }

    @Override
    public int getItemCount() {
        return listOfImages.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.tv_imageTitle);
        }
    }

    public void updateList(List<ImageMetadata> listOfImages){
          this.listOfImages = listOfImages;
          notifyDataSetChanged();
    }
}
