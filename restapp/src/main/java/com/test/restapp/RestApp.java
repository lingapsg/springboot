package com.test.restapp;

import com.test.restapp.repository.domain.Book;
import com.test.restapp.service.BookService;
import org.apache.lucene.queryparser.xml.FilterBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.Map;

import static org.elasticsearch.index.query.MatchQueryBuilder.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.*;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.test")
public class RestApp implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private BookService bookService;

    public static void main(String[] args)throws Exception{
        SpringApplication.run(RestApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        printElasticSearchInfo();

        bookService.save(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", new Date()));
        bookService.save(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", new Date()));
        bookService.save(new Book("1003", "Apache Solr Basics", "Rambabu Posa", new Date()));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", "Apache Lucene")
                .operator(AND))
                .build();

        Page<Book> book = es.queryForPage(searchQuery, Book.class);

        book.forEach(b -> System.out.println(b));

        Page<Book> books = bookService.findByAuthorLike("Rambab", new PageRequest(0, 10));

        books.forEach(x -> System.out.println(x));
    }

    private void printElasticSearchInfo() {
        System.out.println("--ElasticSearch--");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("--ElasticSearch--");
    }
}

