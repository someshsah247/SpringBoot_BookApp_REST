package com.bookpoc.restbook.util;

import org.springframework.stereotype.Component;

import com.bookpoc.restbook.entity.Books;

@Component
public class BookUtils {
	
	//Book valid
	public boolean bookValid(Books book) {
		if(book != null) {
			if( !book.getBookname().isEmpty() &&
					!book.getBookauthor().isEmpty()	&& book.getPrice()>0) {
				return true;
			}
		}
		return false;
	}

	public boolean idValid(Long id) {
		// TODO Auto-generated method stub
		if(id != null && id >=1)
			return true;
		else
			return false;
	}
}
