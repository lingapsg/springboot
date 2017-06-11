package com.test.restapp.service;

import com.test.restapp.RestApp;
import com.test.restapp.repository.domain.Book;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApp.class)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void setup() {
        /*elasticsearchTemplate.deleteIndex(Book.class);
        elasticsearchTemplate.createIndex(Book.class);
        elasticsearchTemplate.putMapping(Book.class);
        elasticsearchTemplate.refresh(Book.class);*/
    }

    @Test
    public void test1() throws Exception {
        Book book = new Book("1002", "Book1", "Linga", new Date());
        Book testBook = bookService.save(book);
        System.out.println(testBook);
        assertNotNull(testBook.getId());
        assertEquals(testBook.getTitle(), book.getTitle());
        assertEquals(testBook.getAuthor(), book.getAuthor());
        assertEquals(testBook.getDate(), book.getDate());
    }

    @Test
    public void test2() throws Exception {
        String id = "1001";
        Book book = bookService.findOne(id);

        assertEquals(book.getId(), id);
    }

    @Test
    public void test3() throws Exception {
        Iterable<Book> books = bookService.findAll();

        assertTrue(books.iterator().hasNext());
    }

    @Test
    public void test4() throws Exception {
        String author = "Linga";
        Page<Book> books = bookService.findByAuthor(author, new PageRequest(0, 10));
        assertThat(books.getTotalElements(), is(1L));
    }

    @Test
    public void test5() throws Exception {
        String title = "Book1";
        List<Book> books = bookService.findByTitle(title);
        assertThat(books.size(), is(1));
    }

    /*@Test
    public void test6() throws Exception {
        Book book = new Book("1001", "Book1", "Linga", new Date());
        bookService.delete(book);
        Book testBook = bookService.findOne(book.getId());
        assertNull(testBook);
    }*/

    @Test
    public void test() {

    }

}