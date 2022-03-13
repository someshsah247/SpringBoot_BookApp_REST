package com.bookpoc.restbook.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Books {
	
	@Id
	@GeneratedValue
	private Long id;
	private String bookname;
	private String bookauthor;
	private double price;
	private int discountpercent;
	private String ISBN;
	private int d_active = 1;
	private String d_internalComments;
}