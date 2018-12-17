package com.coollime.tinnews.save;

import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coollime.tinnews.R;
import com.coollime.tinnews.common.BaseViewModel;
import com.coollime.tinnews.common.LongPressGesture;
import com.coollime.tinnews.common.TinFragmentManager;
import com.coollime.tinnews.common.Util;
import com.coollime.tinnews.retrofit.response.News;
import com.coollime.tinnews.save.detail.SavedNewsDetailedFragment;
import com.coollime.tinnews.save.detail.TinNewsBottomDialog;

public class SavedNewsViewModel extends BaseViewModel<SavedNewsViewModel.SavedNewsViewHolder> {

    private News news;
    private TinFragmentManager tinFragmentManager;
    private SavedNewsPresenter.DeleteListener deleteListener;
    private static int[] ICON_ARRAY = new int[]{R.drawable.a_news_icon, R.drawable.g_news_icon,
            R.drawable.c_news_icon, R.drawable.y_news_icon, R.drawable.m_news_icon};

    public SavedNewsViewModel(int itemResourceId, News news, TinFragmentManager tinFragmentManager,
                              SavedNewsPresenter.DeleteListener deleteListener) {
        super(itemResourceId);
        this.news = news;
        this.tinFragmentManager = tinFragmentManager;
        this.deleteListener = deleteListener;
    }

    @Override
    public SavedNewsViewHolder createItemViewHolder(View view) {
        return new SavedNewsViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void bindViewHolder(SavedNewsViewHolder holder) {
        if (!Util.isStringEmpty(news.author)) {
            holder.author.setText(news.author);
        }
        holder.description.setText(news.getDescription());
        holder.icon.setImageResource(getDrawable());
        /*
        holder.itemView.setOnClickListener(v -> {
            tinFragmentManager.doFragmentTransaction(SavedNewsDetailedFragment.newInstance(news));
        });
        */
        // There could be two different gestures: click or long press
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(
                holder.itemView.getContext(), new LongPressGesture(new LongPressGesture.GestureListener() {
            @Override
            public void onLongPress() {
                // Confirm the deletion of the selected news item in a bottom dialog
                // TODO(coollime): implement a bottom dialog (confirmation style) to delete the news
                TinNewsBottomDialog dialog = new TinNewsBottomDialog(holder.itemView.getContext());
                dialog.setOnClickListener(view -> {
                    deleteListener.onDelete(news);
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

            @Override
            public void onPress() {
                tinFragmentManager.doFragmentTransaction(SavedNewsDetailedFragment.newInstance(news));
            }
        })
        );
        holder.itemView.setOnTouchListener((view, motionEvent) -> {
            gestureDetectorCompat.onTouchEvent(motionEvent);
            return true;
        });
    }

    // Randomly get an icon for the news source
    private @DrawableRes
    int getDrawable() {
        return ICON_ARRAY[(int) (Math.random() * 5)];
    }

    public static class SavedNewsViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView description;
        ImageView icon;

        public SavedNewsViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.image);
        }
    }
}
