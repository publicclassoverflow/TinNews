package com.coollime.tinnews.save.detail;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.coollime.tinnews.R;
import com.coollime.tinnews.WebViewActivity;
import com.coollime.tinnews.common.BaseViewModel;
import com.coollime.tinnews.common.TinFragmentManager;

import static com.coollime.tinnews.WebViewActivity.URL;

public class ReadmoreViewModel extends BaseViewModel<ReadmoreViewModel.ReadmoreViewModelHolder> {
    private final String uri;
    private final TinFragmentManager tinFragmentManager;

    public ReadmoreViewModel(String uri, TinFragmentManager tinFragmentManager) {
        super(R.layout.readmore_layout);
        this.uri = uri;
        this.tinFragmentManager = tinFragmentManager;
    }

    @Override
    public ReadmoreViewModelHolder createItemViewHolder(View view) {
        return new ReadmoreViewModelHolder(view);
    }

    @Override
    public void bindViewHolder(ReadmoreViewModelHolder holder) {
        holder.readMore.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);   // Underline the text
        holder.readMore.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(URL, uri);
            tinFragmentManager.startActivityWithBundle(WebViewActivity.class, false, bundle);
        });
    }

    static class ReadmoreViewModelHolder extends RecyclerView.ViewHolder {
        TextView readMore;
        ReadmoreViewModelHolder(View itemView) {
            super(itemView);
            readMore = itemView.findViewById(R.id.readmore);
        }
    }
}
