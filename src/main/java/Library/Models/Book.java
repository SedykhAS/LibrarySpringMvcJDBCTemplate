package Library.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Title must not be empty")
    @Size(min = 2, max = 20, message = "Author name must be greater than 2 and less than 20")
    private String title;
    @NotEmpty(message = "Name of the author must not be empty")
    @Size(min = 2, max = 20, message = "Author name must be greater than 2 and less than 20")
    private String author;
    @Min(value = 1000, message = "Year of book creation must be greater than 1000")
    private int year;

    public Book() {

    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
