package com.bookpoc.restbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookpoc.restbook.entity.Books;

public interface BookRepository extends JpaRepository<Books, Long> {

}
