package com.farukyildiz.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farukyildiz.sys.entity.ImageModel;
import com.farukyildiz.sys.entity.Movie;

@Service
public interface MovieService {
	Movie createMovie(Movie movie);
	List<Movie> getMovies();
	Movie getMovie(Long id);
	Movie updateMovie(Long id,Movie movie,ImageModel imageModel);
	Boolean deleteMovie(Long id);
}
