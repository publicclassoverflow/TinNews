package com.coollime.tinnews.save.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.coollime.tinnews.R;

public class TinNewsBottomDialog extends BottomSheetDialog {
    private View.OnClickListener listener;

    public TinNewsBottomDialog(@NonNull Context context) {
        super(context, R.style.BottomSheetDialog);
        setContentView(R.layout.bottomdialog_layout);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onStart() {
        this.findViewById(R.id.delete).setOnClickListener(listener);
        this.findViewById(R.id.back).setOnClickListener(v -> {
            dismiss();
        });
    }
}
