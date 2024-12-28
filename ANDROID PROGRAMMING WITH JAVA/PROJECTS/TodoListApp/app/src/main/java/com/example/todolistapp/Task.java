package com.example.todolistapp;

public class Task {
    private int id;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private String description;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Task(String title,String desc) {
        this.title = title;
        this.description = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
