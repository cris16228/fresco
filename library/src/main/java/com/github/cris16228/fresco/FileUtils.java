package com.github.cris16228.fresco;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    public FileUtils() {

    }

    protected Bitmap decodeFile(File file) {
        if (file == null || !file.exists()) return null;
        boolean fullResolution = true;
        if (fullResolution) {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fis, null, options);
            int originalWidth = options.outWidth;
            int originalHeight = options.outHeight;
            if (originalWidth <= 0 || originalHeight <= 0) return null;

            int scale = 1;
            int targetSize = 1024;
            while (originalWidth / 2 > targetSize && originalHeight / 2 > targetSize) {
                originalWidth /= 2;
                originalHeight /= 2;
                scale *= 2;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                return BitmapFactory.decodeStream(fileInputStream, null, options);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    protected void copyStream(InputStream is, OutputStream os) {
        try {
            byte[] buffer = new byte[8192];
            int count;
            long progress = 0;
            while ((count = is.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                progress += count;
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException ignored) {

            }
        }
    }
}
