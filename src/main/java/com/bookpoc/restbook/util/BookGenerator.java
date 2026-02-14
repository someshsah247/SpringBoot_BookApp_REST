package com.bookpoc.restbook.util;


import com.bookpoc.restbook.entity.Books;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

// used to generate unique records to insert in DB
public class BookGenerator {

    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private static final Random RANDOM = new Random();

    public static Books generateUniqueBook() {
        Long id = (long) COUNTER.getAndIncrement(); // Auto-increment ID
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8); // Unique part

        Books book = new Books();
        book.setId(id);
        book.setBookname("BookName_" + uniqueSuffix);
        book.setBookauthor("Author_" + uniqueSuffix);
        book.setPrice(RANDOM.nextInt(500) + 50);  // Generates random price between 50–550
        book.setDiscountpercent(RANDOM.nextInt(10)); // 0–9%
        book.setD_active(1);
        book.setD_internalComments("Generated uniquely at " + System.currentTimeMillis());
        book.setISBN("ISBN-" + uniqueSuffix);
        return book;
    }

}
