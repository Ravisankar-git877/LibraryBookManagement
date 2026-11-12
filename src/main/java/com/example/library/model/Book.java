
package com.example.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Book {

	private Long bookId;

	@NotBlank(message = "Book name is required")
	private String bookName;

	@NotBlank(message = "Author name is required")
	private String author;

	@NotNull(message = "Price is required")
	@Positive(message = "Price must be positive")
	private Double price;

	private Boolean available;

	public Book() {
	}

	public Book(Long bookId, String bookName, String author, Double price, Boolean available) {

		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.price = price;
		this.available = available;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
