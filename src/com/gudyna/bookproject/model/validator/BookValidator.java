package com.gudyna.bookproject.model.validator;

import com.gudyna.bookproject.model.entity.Book;

public class BookValidator {
    private static final int MIN_LENGTH_NAME = 1;
    private static final int MIN_YEAR = 1888;
    private static final int MAX_YEAR = 2020;
    private static final int MIN_PAGES = 10;
    private static final int MAX_PAGES = 776;
    private static final double MIN_PRICE = 133.11;
    private static final double MAX_PRICE = 1111.9;
    private static final int NAME_INDEX = 1;
    private static final int YEAR_INDEX = 2;
    private static final int PAGES_INDEX = 3;
    private static final int PRICE_INDEX = 4;

    public boolean validateBook(Book book) {
        if (book == null) {
            return false;
        }
        boolean answer = false;
        if (validateName(book.getName()) && validateYear(book.getYearCreation()) && validatePages(book.getCountPages()) && validatePrice(book.getPrice())) {
            answer = true;
        }
        return answer;
    }

    public boolean validateBookTags(String[] bookTags) {
        if (bookTags == null) {
            return false;
        }
        boolean answer = true;
        for (String item : bookTags) {
            if (item.isEmpty()) {
                answer = false;
                break;
            }
        }
        try {
            int year = Integer.parseInt(bookTags[YEAR_INDEX]);
            int pages = Integer.parseInt(bookTags[PAGES_INDEX]);
            double price = Double.parseDouble(bookTags[PRICE_INDEX]);
            if (validateName(bookTags[NAME_INDEX]) && validateYear(year) && validatePages(pages) && validatePrice(price)) {
                answer = true;
            }
        } catch (NumberFormatException e) {
            answer = false;
        }
        return answer;
    }

    public boolean validateName(String name) {
        if (name == null) {
            return false;
        }
        return name.length() >= MIN_LENGTH_NAME;
    }

    public boolean validateYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    public boolean validatePages(int pages) {
        return pages >= MIN_PAGES && pages <= MAX_PAGES;
    }

    public boolean validatePrice(double price) {
        return price >= MIN_PRICE && price <= MAX_PRICE;
    }

}
