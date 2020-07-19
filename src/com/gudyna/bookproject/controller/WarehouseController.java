package com.gudyna.bookproject.controller;

import com.gudyna.bookproject.model.booktag.BookTag;
import com.gudyna.bookproject.model.entity.Book;
import com.gudyna.bookproject.model.exception.ProgramException;
import com.gudyna.bookproject.service.BookService;

import java.util.List;

public class WarehouseController {
    private static WarehouseController instance;

    private WarehouseController() {
    }

    public WarehouseController getInstance() {
        if (instance == null) {
            return new WarehouseController();
        }
        return instance;
    }

    public boolean addBook(Book book) {
        BookService service = new BookService();
        boolean result = false;
        try {
            result = service.addBook(book);
        } catch (ProgramException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean removeBook(Book book) {
        BookService service = new BookService();
        boolean result = false;
        try {
            result = service.removeBook(book);
        } catch (ProgramException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Book> findByTag(BookTag tag, String parameter) {
        BookService service = new BookService();
        List<Book> findList = null;
        try {
            findList = service.findByTag(tag, parameter);
        } catch (ProgramException e) {
            System.out.println(e.getMessage());
        }
        return findList;
    }

    public List<Book> sortByTag(BookTag tag) {
        BookService service = new BookService();
        List<Book> sortList = null;
        try {
            sortList = service.sortByTag(tag);
        } catch (ProgramException e) {
            System.out.println(e.getMessage());
        }
        return sortList;
    }
}
