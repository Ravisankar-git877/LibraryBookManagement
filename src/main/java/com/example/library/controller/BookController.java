
package com.example.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.library.model.Book;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService service;

	public BookController(BookService service) {
		this.service = service;
	}

	// 1. ADD BOOK
	@PostMapping
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.addBook(book));
	}

	// 2. GET ALL BOOKS
	@GetMapping
	public List<Book> getAllBooks() {

		return service.getAllBooks();
	}

	// 3. GET BOOK BY ID
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {

		return service.getBookById(id);
	}

	// 4. UPDATE COMPLETE BOOK
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {

		return service.updateBook(id, book);
	}

	// 5. PARTIAL UPDATE
	@PatchMapping("/{id}")
	public Book patchBook(@PathVariable Long id, @RequestBody Book book) {

		return service.patchBook(id, book);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id) {

		service.deleteBook(id);

		return ResponseEntity.ok("Book deleted successfully!");
	}

	// 7. SEARCH BOOK BY NAME
	@GetMapping("/search")
	public List<Book> searchByName(@RequestParam String name) {

		return service.searchByName(name);
	}

	// 8. SEARCH BOOK BY AUTHOR
	@GetMapping("/author/{author}")
	public List<Book> searchByAuthor(@PathVariable String author) {

		return service.searchByAuthor(author);
	}

	// 9. GET AVAILABLE BOOKS
	@GetMapping("/available")
	public List<Book> getAvailableBooks() {

		return service.getAvailableBooks();
	}

	// 10. GET UNAVAILABLE BOOKS
	@GetMapping("/unavailable")
	public List<Book> getUnavailableBooks() {

		return service.getUnavailableBooks();
	}

	// 11. UPDATE AVAILABILITY
	@PatchMapping("/{id}/availability")
	public Book updateAvailability(@PathVariable Long id, @RequestParam Boolean available) {

		return service.updateAvailability(id, available);
	}

	// 12. MARK BOOK AVAILABLE
	@PatchMapping("/{id}/available")
	public Book markAvailable(@PathVariable Long id) {

		return service.markAvailable(id);
	}

	// 13. MARK BOOK UNAVAILABLE
	@PatchMapping("/{id}/unavailable")
	public Book markUnavailable(@PathVariable Long id) {

		return service.markUnavailable(id);
	}

	// 14. DELETE ALL BOOKS
	@DeleteMapping
	public ResponseEntity<Void> deleteAllBooks() {

		service.deleteAllBooks();

		return ResponseEntity.noContent().build();
	}

	// 15. GET TOTAL BOOK COUNT
	@GetMapping("/count")
	public int getBookCount() {

		return service.getBookCount();
	}

	// 16. GET AVAILABLE BOOK COUNT
	@GetMapping("/count/available")
	public long getAvailableCount() {

		return service.getAvailableCount();
	}

	// 17. GET UNAVAILABLE BOOK COUNT
	@GetMapping("/count/unavailable")
	public long getUnavailableCount() {

		return service.getUnavailableCount();
	}

	// 18. GET BOOKS ABOVE PRICE
	@GetMapping("/price/above/{price}")
	public List<Book> getBooksAbovePrice(@PathVariable Double price) {

		return service.getBooksAbovePrice(price);
	}

	// 19. GET BOOKS BELOW PRICE
	@GetMapping("/price/below/{price}")
	public List<Book> getBooksBelowPrice(@PathVariable Double price) {

		return service.getBooksBelowPrice(price);
	}

	// 20. SORT BOOKS BY PRICE
	@GetMapping("/sort/price")
	public List<Book> sortByPrice() {

		return service.sortByPrice();
	}
}
