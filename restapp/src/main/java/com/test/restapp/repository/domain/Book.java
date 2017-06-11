package com.test.restapp.repository.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Document(indexName = "book", type = "books")
public class Book {

    @Id
    private String id;

    @MultiField(
            mainField = @Field(type = String),
            otherFields = {
                    @InnerField(index = not_analyzed, suffix = "test", type = String)
            }
    )
    private String title;
    private String author;
    private Date date;

    public Book() {
    }

    public Book(String id, String title, String author, Date date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }
}
