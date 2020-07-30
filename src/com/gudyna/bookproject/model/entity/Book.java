package com.gudyna.bookproject.model.entity;

public class Book {
    private String id;
    private String name;
    private int yearCreation;
    private int countPages;
    private int price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearCreation() {
        return yearCreation;
    }

    public void setYearCreation(int yearCreation) {
        this.yearCreation = yearCreation;
    }

    public int getCountPages() {
        return countPages;
    }

    public void setCountPages(int countPages) {
        this.countPages = countPages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public BuilderBook createBuilder(){
        return new Book().new BuilderBook();
    }
    public class BuilderBook{
        private BuilderBook() {
            Book.this.id = "";
            Book.this.name = "";
            Book.this.yearCreation =0;
            Book.this.price=0;
            Book.this.countPages = 0;
        }
        public BuilderBook setId(String id) {
            Book.this.id = id;
            return this;
        }

        public BuilderBook setName(String name) {
            Book.this.name = name;
            return this;
        }

        public BuilderBook setPrice(int price) {
            Book.this.price = price;
            return this;
        }

        public BuilderBook setYear(int year) {
            Book.this.yearCreation = year;
            return this;
        }

        public BuilderBook setPages(int pages) {
            Book.this.countPages = pages;
            return this;
        }

        public Book build() {
            return Book.this;
        }
    }
    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return getYearCreation() == book.getYearCreation() &&
                getCountPages() == book.getCountPages() &&
                Double.compare(book.getPrice(), getPrice()) == 0 &&
                getId().equals(book.getId()) &&
                getName().equals(book.getName());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + yearCreation;
        result = 31 * result + countPages;
        result = 31 * result + (int)price;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book{\n");
        builder.append("id='");
        builder.append(id);
        builder.append('\'');
        builder.append("\n");
        builder.append("name='");
        builder.append(name);
        builder.append('\'');
        builder.append("\n");
        builder.append("yearCreation=");
        builder.append(yearCreation);
        builder.append('\'');
        builder.append("\n");
        builder.append("countPages=");
        builder.append(countPages);
        builder.append('\'');
        builder.append("\n");
        builder.append("price=");
        builder.append(price);
        builder.append('\'');
        builder.append("\n}");
        return builder.toString();
    }
}
