package com.coollime.tinnews.save.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.coollime.tinnews.R;
import com.coollime.tinnews.common.BaseViewModel;
import com.squareup.picasso.Picasso;

public class ImageViewModel extends BaseViewModel<ImageViewModel.ImageViewModelHolder> {
    private final String uri;
    public ImageViewModel(String uri) {
        super(R.layout.image_layout);
        this.uri = uri;
    }

    @Override
    public ImageViewModelHolder createItemViewHolder(View view) {
        return new ImageViewModelHolder(view);
    }

    @Override
    public void bindViewHolder(ImageViewModelHolder holder) {
        // Use Picasso to load the image
        Picasso.get().load(uri).into(holder.image);
    }

    static class ImageViewModelHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ImageViewModelHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
