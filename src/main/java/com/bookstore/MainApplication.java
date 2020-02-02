package com.bookstore;

import com.bookstore.dto.AuthorDto;
import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\nFetch the first 5 authors: \n"
                    + bookstoreService.fetchFirst5()
                    + "\n\n");
            
            System.out.println("\nFetch the first 5 authors by age equal to 56: \n"
                    + bookstoreService.fetchFirst5ByAge(56)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by age greater than or equal to 30: \n"
                    + bookstoreService.fetchFirst5ByAgeGreaterThanEqual(30)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by age less than 35: \n"
                    + bookstoreService.fetchFirst5ByAgeLessThan(35)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by age equal to 56 ordered descending by name: \n"
                    + bookstoreService.fetchFirst5ByAgeOrderByNameDesc(56)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by genre equal to History ordered ascending by age: \n"
                    + bookstoreService.fetchFirst5ByGenreOrderByAgeAsc("History")
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by age greater than or equal to 40 ordered ascending by name: \n"
                    + bookstoreService.fetchFirst5ByAgeGreaterThanEqualOrderByNameAsc(40)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by genre Horror and age less than 50 ordered descending by name: \n"
                    + bookstoreService.fetchFirst5ByGenreAndAgeLessThanOrderByNameDesc("Horror", 50)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors ordered ascending by age as DTO: \n");
            List<AuthorDto> authors = bookstoreService.fetchFirst5ByOrderByAgeAsc();
            authors.forEach(a -> System.out.println("Author:" + a.getName() + ", " + a.getAge()));
        };
    }
}
/*
 * 
 * How To Use Query Creation Mechanism For JPA To Limit Result Size

Description: Spring Data comes with the query creation mechanism for JPA that is capable to interpret a query method name and convert it into a SQL query in the proper dialect. This is possible as long as we respect the naming conventions of this mechanism. This is an application that exploit this mechanism to write queries that limit the result size. Basically, the name of the query method instructs Spring Data how to add the LIMIT (or similar clauses depending on the RDBMS) clause to the generated SQL queries.

Key points:

define a Spring Data regular repository (e.g., AuthorRepository)
write query methods respecting the query creation mechanism for JPA naming conventions
Examples:
- List<Author> findFirst5ByAge(int age);
- List<Author> findFirst5ByAgeGreaterThanEqual(int age);
- List<Author> findFirst5ByAgeLessThan(int age);
- List<Author> findFirst5ByAgeOrderByNameDesc(int age);
- List<Author> findFirst5ByGenreOrderByAgeAsc(String genre);
- List<Author> findFirst5ByAgeGreaterThanEqualOrderByNameAsc(int age);
- List<Author> findFirst5ByGenreAndAgeLessThanOrderByNameDesc(String genre, int age);
- List<AuthorDto> findFirst5ByOrderByAgeAsc();
- Page<Author> queryFirst10ByName(String name, Pageable p);
- Slice<Author> findFirst10ByName(String name, Pageable p);

The list of supported keywords is listed below:
 * 
 * 
 */
