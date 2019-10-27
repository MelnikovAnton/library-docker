package ru.otus.library.services.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.library.integration.BookIntegrationService;
import ru.otus.library.model.Book;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("bookService")
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookIntegrationService bookIntegrationService;


    @Override
    @HystrixCommand(commandKey="bookCommands", fallbackMethod="buildFallbackBook")
    public Book saveBook(Book book) {
        log.info("Save book " + book);
        return bookIntegrationService.createBook(book);
    }

    @Override
    @HystrixCommand(commandKey="bookCommands", fallbackMethod="buildFallbackBooks")
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    @Override
    @HystrixCommand(commandKey="bookCommands", fallbackMethod="buildFallbackBooks")
    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthorsNameContains(author);
    }

    @Override
    @HystrixCommand(commandKey="bookCommands", fallbackMethod="buildFallbackBooks")
    public List<Book> findBooksByGenre(String genre) {
        return bookRepository.findByGenresNameContains(genre);
    }

    @Override
    @HystrixCommand(commandKey="bookCommands", fallbackMethod="buildFallbackBookOptional")
    public Optional<Book> findById(String id) {
        try {
            return bookRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Return Empty result.", e);
            return Optional.empty();
        }

    }

    @Override
    @HystrixCommand(commandKey="bookCommands", fallbackMethod="buildFallbackBookDelete")
    public String delete(Book book) {
        log.info("Delete book " + book);
        bookIntegrationService.deleteBook(book);
        return book.getId();
    }

    @Override
    @HystrixCommand(commandKey="bookCommands", fallbackMethod="buildFallbackBooks")
    public List<Book> findAll() {
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bookRepository.findAll();
    }

    @Override
    public void addRelations(Book book) {
        bookRepository.save(book);
    }

    public Book buildFallbackBook(Book book) {
        book.setId("Unknown");
        log.warn("Hystrix fallback. Return book: {}", book);
        return book;
    }

    public List<Book> buildFallbackBooks(String book) {
        log.warn("Hystrix fallback. Return empty List");
        return new ArrayList<>();
    }
    public List<Book> buildFallbackBooks() {
        log.warn("Hystrix fallback. Return empty List");
        return new ArrayList<>();
    }

    public Optional<Book> buildFallbackBookOptional(String book) {
        log.warn("Hystrix fallback. Return empty List");
        return Optional.empty();
    }

    public String buildFallbackBookDelete(Book book) {
        log.warn("Hystrix fallback for delete book {}.", book);
        return "N/A";
    }
}
