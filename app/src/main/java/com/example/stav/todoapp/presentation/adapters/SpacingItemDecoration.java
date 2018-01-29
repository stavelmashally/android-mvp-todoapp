package com.example.stav.todoapp.presentation.adapters;

/**
 * Created by stav on 1/25/18.
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing;

    public SpacingItemDecoration(int spacing) {
        mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int position = parent.getChildViewHolder(view).getAdapterPosition();
        int itemCount = state.getItemCount();
        outRect.left = mSpacing;
        outRect.right = mSpacing;
        outRect.top = mSpacing;
        outRect.bottom = position == itemCount - 1 ? mSpacing : 0;
    }
}
