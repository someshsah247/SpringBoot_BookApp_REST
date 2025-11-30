package com.bookpoc.restbook.service;

import java.util.List;
import java.util.Optional;

import com.bookpoc.restbook.util.BookGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpoc.restbook.entity.Books;
import com.bookpoc.restbook.repository.BookRepository;

import javax.annotation.PostConstruct;

@Service
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

	public List<Books> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

    public Optional<Books> getBookById(Long id) {
        // TODO Auto-generated method stub
        Optional<Books> book = bookRepository.findById(id);
        return book;
    }

	public Books saveBook(Books book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
		
	}

	public Books updateBook(Books book, Long id) {
		book.setId(id);
		book.setD_internalComments("update from URI");
		return bookRepository.save(book);
		
	}

	public String deleteBook(Long id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);
		return "Deleted Book id : "+id;
	}

}
