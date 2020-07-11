package com.gudyna.bookproject.service;

public class BookService {
    public static final BookService service = null;
    private BookService(){

    }
    public static BookService getInstance() {
        if(service==null){
        }
        return service;
    }
}
