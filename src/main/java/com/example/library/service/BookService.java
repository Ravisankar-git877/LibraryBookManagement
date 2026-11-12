
package com.example.library.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.library.model.Book;

@Service
public class BookService {

	private final List<Book> books = new ArrayList<>();

	private final AtomicLong idGenerator = new AtomicLong(1);

	// 1. ADD BOOK
	public Book addBook(Book book) {

		book.setBookId(idGenerator.getAndIncrement());

		if (book.getAvailable() == null) {
			book.setAvailable(true);
		}

		books.add(book);

		return book;
	}

	// 2. GET ALL BOOKS
	public List<Book> getAllBooks() {

		return new ArrayList<>(books);
	}

	// 3. GET BOOK BY ID
	public Book getBookById(Long id) {

		return books.stream().filter(b -> b.getBookId().equals(id)).findFirst()
				.orElseThrow(() -> new NoSuchElementException("Book not found with ID: " + id));
	}

	// 4. UPDATE COMPLETE BOOK
	public Book updateBook(Long id, Book newBook) {

		Book book = getBookById(id);

		book.setBookName(newBook.getBookName());
		book.setAuthor(newBook.getAuthor());
		book.setPrice(newBook.getPrice());
		book.setAvailable(newBook.getAvailable());

		return book;
	}

	// 5. PARTIAL UPDATE BOOK
	public Book patchBook(Long id, Book updates) {

		Book book = getBookById(id);

		if (updates.getBookName() != null) {
			book.setBookName(updates.getBookName());
		}

		if (updates.getAuthor() != null) {
			book.setAuthor(updates.getAuthor());
		}

		if (updates.getPrice() != null) {
			book.setPrice(updates.getPrice());
		}

		if (updates.getAvailable() != null) {
			book.setAvailable(updates.getAvailable());
		}

		return book;
	}

	// 6. DELETE BOOK BY ID
	public void deleteBook(Long id) {

		books.remove(getBookById(id));
	}

	// 7. SEARCH BOOK BY NAME
	public List<Book> searchByName(String name) {

		return books.stream().filter(b -> b.getBookName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}

	// 8. SEARCH BOOK BY AUTHOR
	public List<Book> searchByAuthor(String author) {

		return books.stream().filter(b -> b.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
	}

	// 9. GET AVAILABLE BOOKS
	public List<Book> getAvailableBooks() {

		return books.stream().filter(b -> Boolean.TRUE.equals(b.getAvailable())).collect(Collectors.toList());
	}

	// 10. GET UNAVAILABLE BOOKS
	public List<Book> getUnavailableBooks() {

		return books.stream().filter(b -> Boolean.FALSE.equals(b.getAvailable())).collect(Collectors.toList());
	}

	// 11. UPDATE AVAILABILITY
	public Book updateAvailability(Long id, Boolean available) {

		if (available == null) {
			throw new IllegalArgumentException("Availability cannot be null");
		}

		Book book = getBookById(id);

		book.setAvailable(available);

		return book;
	}

	// 12. MARK BOOK AVAILABLE
	public Book markAvailable(Long id) {

		return updateAvailability(id, true);
	}

	// 13. MARK BOOK UNAVAILABLE
	public Book markUnavailable(Long id) {

		return updateAvailability(id, false);
	}

	// 14. DELETE ALL BOOKS
	public void deleteAllBooks() {

		books.clear();
	}

	// 15. GET TOTAL BOOK COUNT
	public int getBookCount() {

		return books.size();
	}

	// 16. GET AVAILABLE BOOK COUNT
	public long getAvailableCount() {

		return getAvailableBooks().size();
	}

	// 17. GET UNAVAILABLE BOOK COUNT
	public long getUnavailableCount() {

		return getUnavailableBooks().size();
	}

	// 18. GET BOOKS ABOVE PRICE
	public List<Book> getBooksAbovePrice(Double price) {

		return books.stream().filter(b -> b.getPrice() > price).collect(Collectors.toList());
	}

	// 19. GET BOOKS BELOW PRICE
	public List<Book> getBooksBelowPrice(Double price) {

		return books.stream().filter(b -> b.getPrice() < price).collect(Collectors.toList());
	}

	// 20. SORT BOOKS BY PRICE
	public List<Book> sortByPrice() {

		return books.stream().sorted(Comparator.comparing(Book::getPrice)).collect(Collectors.toList());
	}
}
