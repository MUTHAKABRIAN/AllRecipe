package com.moringaschool.allrecipe.util;

public interface ItemTouchHelper {
    boolean onItemMove(int fromPosition,int toPosition);
    void onItemDismiss(int position);
}
