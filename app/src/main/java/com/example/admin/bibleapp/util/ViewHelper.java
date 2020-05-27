package com.example.admin.bibleapp.util;

import android.view.View;

public class ViewHelper {
    public static boolean isViewHit(View view, View origin, int x, int y) {
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);
        int[] parentLocation = new int[2];
        origin.getLocationOnScreen(parentLocation);
        int screenX = parentLocation[0] + x;
        int screenY = parentLocation[1] + y;
        if (screenX < viewLocation[0] || screenX >= viewLocation[0] + view.getWidth() || screenY < viewLocation[1] || screenY >= viewLocation[1] + view.getHeight()) {
            return false;
        }
        return true;
    }
}
