package com.github.cris16228.fresco;

import androidx.annotation.Nullable;

public class StringUtils {

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
