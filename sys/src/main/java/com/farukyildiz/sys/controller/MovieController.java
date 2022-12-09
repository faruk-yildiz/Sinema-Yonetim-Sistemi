package com.farukyildiz.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import com.farukyildiz.sys.entity.ImageModel;
import com.farukyildiz.sys.entity.Movie;
import com.farukyildiz.sys.service.MovieService;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieController {
	
	private final MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Movie> createMovie(@RequestPart("movie") Movie movie,@RequestPart("image") MultipartFile image){
		try {
			ImageModel imageModel=uploadImage(image);
			movie.setImageModel(imageModel);
			Movie resultMovie=movieService.createMovie(movie);
			if(resultMovie!=null)
				return ResponseEntity.ok(movie);
			else
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	
	public ImageModel uploadImage(MultipartFile multipartFile) throws IOException {
		ImageModel imageModel =new ImageModel(
				multipartFile.getOriginalFilename(),
				multipartFile.getContentType(),
				multipartFile.getBytes()
				);
		return imageModel;
	}
	
	@GetMapping
	public ResponseEntity<List<Movie>> getMovies(){
		List<Movie> movies=movieService.getMovies();
		return ResponseEntity.ok(movies);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable("id") Long id){
		Movie movie=movieService.getMovie(id);
		return ResponseEntity.ok(movie);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id,@RequestPart("movie") Movie movie,@RequestPart("image") MultipartFile image){
		ImageModel imageModel=new ImageModel();
		try {
			imageModel.setName(image.getOriginalFilename());
			imageModel.setType(image.getContentType());
			imageModel.setPicByte(image.getBytes());
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		Movie resultMovie=movieService.updateMovie(id, movie,imageModel);
		if(resultMovie!=null)
			return ResponseEntity.ok(resultMovie);
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteMovie(@PathVariable("id") Long id) {
		Boolean status=movieService.deleteMovie(id);
		return ResponseEntity.ok(status);
	}
	
}
