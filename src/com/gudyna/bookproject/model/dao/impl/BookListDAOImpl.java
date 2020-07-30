package com.gudyna.bookproject.model.dao.impl;

import com.gudyna.bookproject.model.dao.BookListDao;
import com.gudyna.bookproject.model.entity.Book;
import com.gudyna.bookproject.model.entity.BookWarehouse;
import com.gudyna.bookproject.model.exception.DAOException;
import com.gudyna.bookproject.model.sqlconnect.BaseHelper;
import com.gudyna.bookproject.model.sqlconnect.SqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



public class BookListDAOImpl implements BookListDao {
    private final BookWarehouse bookWarehouse;
    public BookListDAOImpl() {
        bookWarehouse=BookWarehouse.getInstance();
    }
    @Override
    public boolean addBook(Book book) throws DAOException {
        BaseHelper baseHelper = new BaseHelper();
        ResultSet resultSet = null;
        try(Connection connection = SqlConnector.connect();
            PreparedStatement statementAdd =
                    baseHelper.prepareStatementAdd(connection, book);
        PreparedStatement statementSelect =
                baseHelper.prepareStatementSelect(connection, book)) {
            statementAdd.executeUpdate();
            resultSet = statementSelect.executeQuery();
            return resultSet.next();
        } catch (SQLException e){
            throw  new DAOException(e);
        } finally{
            close(resultSet);
        }
    }

    @Override
    public List<Book> removeBook(Book book) throws DAOException {
        BaseHelper helper = new BaseHelper();
        List<Book> removedBooks = new ArrayList<>();
        try (Connection connection = SqlConnector.connect();
             PreparedStatement statementSelect =
                     helper.prepareStatementSelect(connection, book);
             ResultSet resultSet = statementSelect.executeQuery()) {
            while (resultSet.next()) {
                removedBooks.add(book);
                resultSet.deleteRow();
            }
            if (removedBooks.isEmpty()) {
                throw new DAOException("There is no such book in warehouse!");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return removedBooks;
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
        sortedList = books.stream().sorted(Comparator.comparing(Book::getName)).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByPrice() {
        List<Book> books = bookWarehouse.findAll();
        List<Book> sortedList;
        sortedList = books.stream().sorted(Comparator.comparingDouble(Book::getPrice)).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByYear() {
        List<Book> books = bookWarehouse.findAll();
        List<Book> sortedList;
        sortedList = books.stream().sorted(Comparator.comparingInt(Book::getYearCreation)).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByPages() {
        List<Book> books = bookWarehouse.findAll();
        List<Book> sortedList;
        sortedList = books.stream().sorted(Comparator.comparingInt(Book::getCountPages)).collect(Collectors.toList());
        return sortedList;
    }
    void close(ResultSet resultSet) throws DAOException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Error during closing result set!");
            }
        }
    }
}
