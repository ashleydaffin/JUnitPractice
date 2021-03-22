package bookstoread;

import java.time.LocalDate;

public class Book {
    private final String title;
    private final String author;
    private final LocalDate publishedOn;

    public Book(String title, String author, LocalDate publishedOn){
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public LocalDate getPublishedOn(){
        return publishedOn;
    }

    @Override
    public String toString(){
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ". publishedOn =" + publishedOn +
                '}';
    }
}
