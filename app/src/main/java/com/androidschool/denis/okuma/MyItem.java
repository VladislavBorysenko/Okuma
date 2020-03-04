package com.androidschool.denis.okuma;

import android.os.Build;

import java.util.Objects;

import androidx.annotation.RequiresApi;

public class MyItem {
    private String category;
    private String title;

    public MyItem() {
    }

    public MyItem(String category, String title) {
        this.category = category;
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyItem myItem = (MyItem) o;
        return Objects.equals(category, myItem.category) &&
                Objects.equals(title, myItem.title);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(category, title);
    }
}
