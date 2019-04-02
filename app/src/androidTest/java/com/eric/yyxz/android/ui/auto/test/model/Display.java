package com.eric.yyxz.android.ui.auto.test.model;

/**
 * adb shell wm size
 * 手机分辨率
 */
public class Display {
    private int width;
    private int height;

    public Display() {
    }

    public Display(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return width + "x" + height;
    }
}
