package com.gudyna.bookproject.model.dao.impl;

import com.gudyna.bookproject.model.dao.BookListDao;
import com.gudyna.bookproject.model.entity.Book;
import com.gudyna.bookproject.model.entity.BookWarehouse;
import com.gudyna.bookproject.model.exception.DAOException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookListDAOImpl implements BookListDao {
    private final BookWarehouse bookWarehouse;
    public BookListDAOImpl() {
        bookWarehouse= new BookWarehouse();

    }
    @Override
    public void addBook(Book book) throws DAOException {
        List<Book> books = bookWarehouse.findAll();
        if (books.contains(book)) {
            throw new DAOException("This book already exists!");
        }
        if (bookWarehouse.isFull()) {
            throw new DAOException("Warehouse is full!");
        }
        bookWarehouse.add(book);
    }

    @Override
    public void removeBook(Book book) throws DAOException {
        List<Book> books = bookWarehouse.findAll();
        if (books.contains(book)) {
            throw new DAOException("This book already exists!");
        }
        bookWarehouse.remove(book);
    }

    @Override
    public List<Book> findBookById(String id) {
        List<Book> books = bookWarehouse.findAll();
        return books.stream().filter(book -> book.getId().equals(id)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByName(String name) {
        List<Book> books = bookWarehouse.findAll();
        return books.stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByPrice(double price) {
        List<Book> books = bookWarehouse.findAll();
        return books.stream().filter(book -> book.getPrice()==price).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByYear(int year) {
        List<Book> books = bookWarehouse.findAll();
        return books.stream().filter(book -> book.getYearCreation()==year).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByPages(int pages) {
        List<Book> books = bookWarehouse.findAll();
        return books.stream().filter(book -> book.getCountPages()==pages).collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return bookWarehouse.findAll();
    }

    @Override
    public List<Book> sortBooksById() {
        List<Book> books = bookWarehouse.findAll();
        List<Book> sortedList;
        sortedList = books.stream().sorted(Comparator.comparing(Book::getId)).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByName() {
        List<Book> books = bookWarehouse.findAll();
        List<Book> sortedList;
        sortedList = books.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByPrice() {
        List<Book> books = bookWarehouse.findAll();
        List<Book> sortedList;
        sortedList = books.stream().sorted((o1, o2) -> (int) (o1.getPrice()-o2.getPrice())).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByYear() {
        List<Book> books = bookWarehouse.findAll();
        List<Book> sortedList;
        sortedList = books.stream().sorted((o1, o2) -> o1.getYearCreation()-o2.getYearCreation()).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByPages() {
        List<Book> books = bookWarehouse.findAll();
        List<Book> sortedList;
        sortedList = books.stream().sorted((o1, o2) -> o1.getCountPages()-o2.getCountPages()).collect(Collectors.toList());
        return sortedList;
    }
}
