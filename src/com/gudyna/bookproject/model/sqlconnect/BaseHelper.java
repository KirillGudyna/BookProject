package com.gudyna.bookproject.model.sqlconnect;

import com.gudyna.bookproject.model.booktag.BookTag;
import com.gudyna.bookproject.model.entity.Book;
import com.gudyna.bookproject.model.exception.DAOException;
import com.gudyna.bookproject.model.request.SelectRequest;
import com.gudyna.bookproject.model.request.impl.FindRequestImpl;
import com.gudyna.bookproject.model.request.impl.SortRequestImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseHelper {
    private static final String SQL_INSERT ="INSERT INTO books(name, year, pages,price) VALUES(?,?,?,?)";
    private static final String SQL_SELECT =
            "SELECT id_book, name, year, pages, price " +
                    "FROM books WHERE name=? AND year=? " +
                    "AND pages=? AND price=?";
    private static final String SQL_SELECT_BY_ID =
            "SELECT id_book, name, year, pages, price" +
                    "FROM books WHERE id_book=?";
    private static final String SQL_FIND =
            "SELECT id_book, name, year, pages, " +
                    "price FROM books WHERE %s=?";
    private static final String SQL_ORDER =
            "SELECT id_book, name, year, pages, " +
                    "pric FROM books ORDER BY %s";

    public PreparedStatement prepareStatementAdd(Connection connection, Book book) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, book.getName());
            statement.setInt(2, book.getYearCreation());
            statement.setInt(3, book.getCountPages());
            statement.setInt(4, book.getPrice());
            return statement;
        } catch (SQLException e) {
            throw new DAOException("Error, while getting statement!", e);
        }
    }

    public PreparedStatement prepareStatementSelect(Connection connection, Book book) throws DAOException {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SQL_SELECT,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, book.getName());
            statement.setInt(2, book.getYearCreation());
            statement.setInt(3, book.getCountPages());
            statement.setInt(4, book.getPrice());
            return statement;
        } catch (SQLException e) {
            throw new DAOException("Error, while getting statement!", e);
        }
    }

    public PreparedStatement prepareStatementSelectById(Connection connection, int bookId) throws DAOException {
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SQL_SELECT_BY_ID,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, bookId);
            return statement;
        } catch (SQLException e) {
            throw new DAOException("Error, while getting statement!", e);
        }
    }

    public PreparedStatement prepareStatementFind(Connection connection, SelectRequest selectRequest) throws DAOException {
        PreparedStatement statement;
        try {
            if (selectRequest instanceof SortRequestImpl) {
                String sqlOrder = String.format(SQL_ORDER,
                        selectRequest.getBookTag().name().toLowerCase());
                statement = connection.prepareStatement(sqlOrder);
            } else {
                String sqlFind = String.format(SQL_FIND,
                        selectRequest.getBookTag().name().toLowerCase());
                statement = connection.prepareStatement(sqlFind);
                statement.setString(1,
                        ((FindRequestImpl) selectRequest).getTagValue());
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting statement!", e);
        }
        return statement;
    }
}
