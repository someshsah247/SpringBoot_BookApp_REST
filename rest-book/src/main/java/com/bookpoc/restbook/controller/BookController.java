package com.bookpoc.restbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookpoc.restbook.entity.Books;
import com.bookpoc.restbook.service.BookService;
import com.bookpoc.restbook.util.BookUtils;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	@Autowired
	BookUtils bookUtils;
	
	@GetMapping("/testing")
	public String welcomeText() {
		return "Working...";
	}
	
	@GetMapping("/books")
	public List<Books> allBooks(){
		return bookService.getAllBooks();
	}
	
	@PostMapping("/book")
	public Books addBook(@RequestBody Books book){
		
		if(bookUtils.bookValid(book)) {
			bookService.saveBook(book);
				return book;
		}
		else
			return new Books();
	}
	
	@PutMapping("/update/{id}")
	public Books updateBookById(@RequestBody Books book, @PathVariable Long id) {
		if(bookUtils.bookValid(book)) {
			bookService.updateBook(book,id);
				return book;
		}
		else
			return new Books();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBookById(@PathVariable Long id) {
		if(bookUtils.idValid(id))
			return bookService.deleteBook(id);
		else
			return "Not a Valid Id.";
	}
	
}
