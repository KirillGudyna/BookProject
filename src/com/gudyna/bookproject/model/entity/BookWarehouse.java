package com.gudyna.bookproject.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookWarehouse {

    private List<Book> books;
    private static final int CAPACITY = 30;
    private static BookWarehouse bookWarehouseInstance;
    private BookWarehouse() {
        books = new ArrayList<>();
    }

    public static BookWarehouse getInstance() {
        if (bookWarehouseInstance == null) {
            bookWarehouseInstance = new BookWarehouse();
        }
        return bookWarehouseInstance;
    }

    public boolean add(Book book) {
        return books.add(book);
    }

    public boolean remove(Book book) {
        return books.remove(book);
    }

    public void removeAll() {
        books = new ArrayList<>();
    }

    public int indexOf(Book book) {
        return books.indexOf(book);
    }

    public boolean isFull() {
        return books.size() < CAPACITY;
    }

    public Book set(int index, Book book) {
        return books.set(index, book);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookWarehouse)) {
            return false;
        }
        BookWarehouse that = (BookWarehouse) o;
        return Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        int result = books.hashCode();
        result = 31 * result + ((Integer) CAPACITY).hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BookWarehouse{\n");
        for(Book book: books){
            builder.append(book);
            builder.append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
