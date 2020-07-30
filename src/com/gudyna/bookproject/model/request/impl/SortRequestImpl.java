package com.gudyna.bookproject.model.request.impl;

import com.gudyna.bookproject.model.booktag.BookTag;
import com.gudyna.bookproject.model.request.SelectRequest;

public class SortRequestImpl implements SelectRequest {
    private final BookTag sortTag;

    public SortRequestImpl(BookTag sortTag) {
        this.sortTag = sortTag;
    }

    @Override
    public BookTag getBookTag() {
        return sortTag;
    }

}
