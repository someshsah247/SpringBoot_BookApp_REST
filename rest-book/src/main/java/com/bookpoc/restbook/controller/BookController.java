package com.bookpoc.restbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Books>> getAllBooks(){
		return ResponseEntity.ok().body(bookService.getAllBooks());
	}

    @GetMapping("/book/id")
    public ResponseEntity<Books> getBookById(@PathVariable("id") int bookId){
        Optional<Books> optionalBooks =  bookService.getBookById(Long.parseLong(String.valueOf(bookId)));
        return optionalBooks.map(books -> ResponseEntity.ok().body(books)).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@PostMapping("/book")
	public ResponseEntity<Books> addBook(@RequestBody Books book){
		
		if(bookUtils.bookValid(book)) {
			bookService.saveBook(book);
				return ResponseEntity.ok(book);
		}
		else
			return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Books> updateBookById(@RequestBody Books book, @PathVariable Long id) {
		if(bookUtils.bookValid(book)) {
			bookService.updateBook(book,id);
				return ResponseEntity.ok(book);
		}
		else
			return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
		if(bookUtils.idValid(id))
			return ResponseEntity.ok(bookService.deleteBook(id));
		else
			return ResponseEntity.notFound().build();  //Not a Valid Id.
	}
	
}
