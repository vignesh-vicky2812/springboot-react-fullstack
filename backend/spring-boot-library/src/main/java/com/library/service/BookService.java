package com.library.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.BookRepository;
import com.library.dao.CheckoutRepository;
import com.library.entity.Book;
import com.library.entity.Checkout;

@Service
@Transactional
public class BookService {

	private BookRepository bookRepository;

	private CheckoutRepository checkoutRepository;

	public BookService(BookRepository bookRepository, CheckoutRepository checkoutRepository) {
		super();
		this.bookRepository = bookRepository;
		this.checkoutRepository = checkoutRepository;
	}

	public Book checkoutBook(String userEmail, Long bookId) throws Exception {
		Optional<Book> book = bookRepository.findById(bookId);

		Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

		if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
			throw new Exception("book doesnt exist or already checked out by user");
		}

		book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
		bookRepository.save(book.get());

		Checkout checkout = new Checkout(userEmail, LocalDate.now().toString(), LocalDate.now().plusDays(7).toString(),
				book.get().getId());

		checkoutRepository.save(checkout);
		return book.get();
	}

	public Boolean checkOutBookByUser(String userEmail, Long bookId) {
		System.out.println("jhe");
		Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

		return validateCheckout != null ? true : false;
	}
	
	public int currentLoansCount(String userEmail) {
		return checkoutRepository.findBooksByUserEmail(userEmail).size();
	}
}