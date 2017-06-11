package com.test.restapp.repository;


import com.test.restapp.repository.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, String> {

    Page<Book> findByAuthor(String author, Pageable pageable);

    List<Book> findByTitle(String title);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"author\": \"?0\"}}]}}")
    Page<Book> findByAuthorNameCustomQuery(String author, Pageable pageable);

    Page<Book> findByAuthorLike(String author, Pageable pageable);
}
