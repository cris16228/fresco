package com.github.cris16228.fresco.interfaces;

import android.graphics.Bitmap;

public interface LoadImage {

    void onSuccess(Bitmap bitmap);

    void onFail();
}
