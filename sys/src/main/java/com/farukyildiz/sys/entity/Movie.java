package com.farukyildiz.sys.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Long id;
	@Column(name = "tittle")
	private String tittle;
	@Column(name = "year")
	private String year;
	@Column(name = "duration")
	private Integer duration;
	@Column(name = "director")
	private String director;
	@Column(name = "description",length = 10000)
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageModel imageModel;
	
	public Movie() {
	}
	
	public Movie(String tittle, String year, Integer duration, String director, String description,
			ImageModel imageModel) {
		this.tittle = tittle;
		this.year = year;
		this.duration = duration;
		this.director = director;
		this.description = description;
		this.imageModel = imageModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ImageModel getImageModel() {
		return imageModel;
	}

	public void setImageModel(ImageModel imageModel) {
		this.imageModel = imageModel;
	}
	
}
	