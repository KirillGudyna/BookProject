package com.gudyna.bookproject.service;

import com.gudyna.bookproject.model.booktag.BookTag;
import com.gudyna.bookproject.model.dao.BookListDao;
import com.gudyna.bookproject.model.dao.impl.BookListDAOImpl;
import com.gudyna.bookproject.model.entity.Book;
import com.gudyna.bookproject.model.exception.DAOException;
import com.gudyna.bookproject.model.exception.ProgramException;
import com.gudyna.bookproject.model.validator.BookValidator;

import java.util.List;
import java.util.function.Function;

public class BookService {
    private BookListDao bookListDAO = new BookListDAOImpl();

    public Book prepareBook(String[] tagsBook) throws ProgramException {
        BookValidator validator = new BookValidator();
        if (validator.validateBookTags(tagsBook)) {
            throw new ProgramException("Invalid book's tags!");
        }
        Book book;
        book = new Book().createBuilder()
                .setId(tagsBook[0])
                .setName(tagsBook[1])
                .setYear(Integer.parseInt(tagsBook[2]))
                .setPages(Integer.parseInt(tagsBook[3]))
                .setPrice(Integer.parseInt(tagsBook[4]))
                .build();
        return book;
    }

    public boolean addBook(Book book) throws ProgramException {
        BookValidator validator = new BookValidator();
        if (!validator.validateBook(book)) {
            throw new ProgramException("Invalid parameters of book!!");
        }
        try {
            return bookListDAO.addBook(book);
        } catch (DAOException e) {
            throw new ProgramException(e.getMessage(), e);
        }
    }

    public boolean removeBook(Book book) throws ProgramException {
        BookValidator validator = new BookValidator();
        if (!validator.validateBook(book)) {
            throw new ProgramException("Invalid parameters of book!!");
        }
        try {
            return bookListDAO.removeBook(book);
        } catch (DAOException e) {
            throw new ProgramException(e.getMessage(), e);
        }
    }

    public List<Book> sortByTag(BookTag bookTag) throws ProgramException {
        if (bookTag == null) {
            throw new ProgramException("Invalid Tag");
        }
        Function<String, List<Book>> sortFunction;
        List<Book> sortedList;
        sortFunction = switch (bookTag) {
            case ID -> value -> bookListDAO.sortBooksById();
            case YEAR -> value -> bookListDAO.sortBooksByYear();
            case NAME -> value -> bookListDAO.sortBooksByName();
            case PAGES -> value -> bookListDAO.sortBooksByPages();
            case PRICE -> value -> bookListDAO.sortBooksByPrice();
        };
        sortedList = sortFunction.apply("");
        return sortedList;
    }

    public List<Book> findByTag(BookTag bookTag, String parameter) throws ProgramException {
        if (bookTag == null || parameter == null) {
            throw new ProgramException("Invalid tag or parameter!!!");
        }
        List<Book> foundList;
        try {
            foundList = switch (bookTag) {
                case ID -> bookListDAO.findBookById(parameter);
                case YEAR -> bookListDAO.findBooksByYear(Integer.parseInt(parameter));
                case NAME -> bookListDAO.findBooksByName(parameter);
                case PAGES -> bookListDAO.findBooksByPages(Integer.parseInt(parameter));
                case PRICE -> bookListDAO.findBooksByPrice(Double.parseDouble(parameter));
            };
        } catch (NumberFormatException e){
            throw new ProgramException("Invalid parameter which you want to parse!", e);
        }
        return foundList;
    }

}
