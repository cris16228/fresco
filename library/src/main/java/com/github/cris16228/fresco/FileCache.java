package com.github.cris16228.fresco;

import android.content.Context;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FileCache {

    public final File cacheDir;

    public FileCache(Context context) {
        cacheDir = new File(context.getCacheDir() + "/fresco");
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url) {
        String file_name = null;
        try {
            file_name = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (file_name != null) {
            return new File(cacheDir, file_name);
        }
        return null;
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public long length() {
        long size = cacheDir.length();
        File[] files = cacheDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file != null && file.isFile()) {
                    size += file.length();
                }
            }
        }
        return size;
    }

    public int size() {
        File[] files = cacheDir.listFiles();
        return files != null ? files.length : 0;
    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
    
    public boolean clear(String url) {
        try {
            String file_name = URLEncoder.encode(url, "UTF-8");
            if (file_name != null) {
                File file = new File(cacheDir, file_name);
                if (file.exists()) {
                    return file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
