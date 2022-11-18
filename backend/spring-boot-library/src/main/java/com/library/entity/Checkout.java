package com.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

@Entity
@Table(name = "checkout")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Checkout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "checkout_date")
	private String checkoutDate;

	@Column(name = "return_date")
	private String returnDate;

	@Column(name = "book_id")
	private Long bookId;

	public Checkout() {

	}

	public Checkout(String userEmail, String checkoutDate, String returnDate, Long bookId) {
		super();
		this.userEmail = userEmail;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
		this.bookId = bookId;
	}

}
