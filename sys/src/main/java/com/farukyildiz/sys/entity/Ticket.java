package com.farukyildiz.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id",nullable = false)
	private Long id;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "phone",nullable = false)
	private String phone;
	
	@Column(name = "movie_id",nullable = false)
	private Long movie_id;
	
	@Column(name = "saloon_id",nullable = false)
	private Long saloon_id;
	
	@Column(name = "date",nullable = false)
	private String date;
	
	@Column(name = "session",nullable = false)
	private String session;

	public Ticket() {
		
	}
	
	public Ticket(String email, String phone, Long movie_id, Long saloon_id, String date, String session) {
		this.email = email;
		this.phone = phone;
		this.movie_id = movie_id;
		this.saloon_id = saloon_id;
		this.date = date;
		this.session = session;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public Long getSaloon_id() {
		return saloon_id;
	}

	public void setSaloon_id(Long saloon_id) {
		this.saloon_id = saloon_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
	
}
