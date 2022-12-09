package com.farukyildiz.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "saloon")
public class Saloon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saloon_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "capacity")
	private Integer capacity;
	
	@Column(name = "expire_date")
	private String expire_date;

	@Column(name = "movie_id")
	private Long movie_id;
	
	@Column(name = "sessions")
	private String sessions;
	
	public Saloon() {
		
	}
	

	public Saloon(String name, Integer capacity, String expire_date, Long movie_id, String sessions) {
		this.name = name;
		this.capacity = capacity;
		this.expire_date = expire_date;
		this.movie_id = movie_id;
		this.sessions = sessions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}

	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}
	
	public String getSessions() {
		return sessions;
	}


	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	
}
