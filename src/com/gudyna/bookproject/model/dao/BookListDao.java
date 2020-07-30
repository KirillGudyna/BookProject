package com.gudyna.bookproject.model.dao;

import com.gudyna.bookproject.model.entity.Book;
import com.gudyna.bookproject.model.exception.DAOException;

import java.util.List;

public interface BookListDao {

    boolean addBook(Book book) throws DAOException;

    List<Book> removeBook(Book book) throws DAOException;

    List<Book> findBookById(String id);

    List<Book> findBooksByName(String name);

    List<Book> findBooksByPrice(double price);

    List<Book> findBooksByYear(int year);

    List<Book> findBooksByPages(int pages);

    List<Book> findAll();

    List<Book> sortBooksById();

    List<Book> sortBooksByName();

    List<Book> sortBooksByPrice();

    List<Book> sortBooksByYear();

    List<Book> sortBooksByPages();
}
