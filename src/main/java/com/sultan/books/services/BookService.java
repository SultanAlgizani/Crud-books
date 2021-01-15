package com.sultan.books.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sultan.books.models.Book;
import com.sultan.books.repositories.BookRepository;
@Service
public class BookService {

    // adding the book repository as a dependency
    private final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    // returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    
    // retrieves one book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    
    //update a book
    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
        if (bookRepository.findById(id).isPresent()) {
            Book b = bookRepository.findById(id).get();
            b.setTitle(title);
            b.setDescription(desc);
            b.setLanguage(lang);
            b.setNumberOfPages(numOfPages);
            return bookRepository.save(b);
        }
        return null;
    }
    
    //delete a book
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

    public Book editBook(@Valid Book book) {
        return bookRepository.save(book);
	}

	
}
