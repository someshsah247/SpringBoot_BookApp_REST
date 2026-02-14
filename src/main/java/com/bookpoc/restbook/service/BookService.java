package com.bookpoc.restbook.service;

import java.util.List;
import java.util.Optional;

import com.bookpoc.restbook.util.BookGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bookpoc.restbook.entity.Books;
import com.bookpoc.restbook.repository.BookRepository;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@CacheConfig(cacheNames = "bookCache") // now no need to write cache names everywhere
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

    // while startup , it will insert 1000 records in DB
//    @PostConstruct
//    public void addBooksInDB(){
//        for (int i = 1; i < 1000; i++) {
//            Books book = BookGenerator.generateUniqueBook();
//            bookRepository.save(book);
//        }
//    }

    // @Cacheable(value = "bookCache")
    @Cacheable
	public List<Books> getAllBooks() {
		// TODO Auto-generated method stub
        log.info("BookService :: getAllBooks :: Hit DB ?");
		return bookRepository.findAll();
	}

    // @Cacheable(value = "bookCache")
    @Cacheable
    public Optional<Books> getBookById(Long id) {
        // TODO Auto-generated method stub
        Optional<Books> book = bookRepository.findById(id);
        return book;
    }

    // @CachePut(value = "bookCache", key = "#result.id")  // use returned object
    @CachePut(key = "#result.id")
    public Books saveBook(Books book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book); // ID generated here, only after persist
		
	}

    // @CachePut(value = "booksCache", key = "#id")
    @CachePut(key = "#id")
	public Books updateBook(Books book, Long id) {
		book.setId(id);
		book.setD_internalComments("update from URI");
		return bookRepository.save(book);
		
	}

    // @CacheEvict(value = "bookCache", key = "#id")  // remove from cache also
    @CacheEvict(key = "#id")
    public String deleteBook(Long id) {
		// TODO Auto-generated method stub
        log.info("BookService :: deleteBook :: Hit DB ?");
		bookRepository.deleteById(id);
		return "Deleted Book id : "+id;
	}

}
