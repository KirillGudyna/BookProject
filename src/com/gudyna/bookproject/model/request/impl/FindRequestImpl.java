package com.gudyna.bookproject.model.request.impl;

import com.gudyna.bookproject.model.booktag.BookTag;
import com.gudyna.bookproject.model.request.SelectRequest;

public class FindRequestImpl implements SelectRequest {
    private final BookTag findTag;
    private final String tagValue;

    public FindRequestImpl(BookTag findTag, String tagValue) {
        this.findTag = findTag;
        this.tagValue = tagValue;
    }

    @Override
    public BookTag getBookTag() {
        return findTag;
    }

    public String getTagValue() {
        return tagValue;
    }
}
