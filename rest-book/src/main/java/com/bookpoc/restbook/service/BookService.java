package com.bookpoc.restbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpoc.restbook.entity.Books;
import com.bookpoc.restbook.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

	public List<Books> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
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
