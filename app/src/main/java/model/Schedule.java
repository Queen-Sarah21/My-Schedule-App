package model;

import android.graphics.Color;

import androidx.annotation.NonNull;

public class Schedule {
    private int id;
    private String description;
    private int textColor;

    public Schedule(int id, String description, int textColor) {
        this.id = id;
        this.description = description;
        this.textColor = textColor;
    }

    public Schedule(int id, String description) {
        this.id = id;
        this.description = description;
        this.textColor= Color.BLACK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }
}
