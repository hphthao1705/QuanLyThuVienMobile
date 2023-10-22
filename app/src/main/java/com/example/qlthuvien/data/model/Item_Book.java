package com.example.qlthuvien.data.model;

public class Item_Book {
    String image_book;
    String name_book;
    String author_book;

    public Item_Book(String image_book, String name_book, String author_book) {
        this.image_book = image_book;
        this.name_book = name_book;
        this.author_book = author_book;
    }

    public String getImage_book() {
        return image_book;
    }

    public void setImage_book(String image_book) {
        this.image_book = image_book;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public String getAuthor_book() {
        return author_book;
    }

    public void setAuthor_book(String author_book) {
        this.author_book = author_book;
    }
}
