package com.farukyildiz.sys.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farukyildiz.sys.entity.ImageModel;
import com.farukyildiz.sys.entity.Movie;
import com.farukyildiz.sys.repository.MovieRepository;
import com.farukyildiz.sys.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie createMovie(Movie movie) {
		if(!movie.getTittle().isEmpty() && !movie.getYear().isEmpty() && !movie.getDirector().isEmpty() && movie.getDuration()!=null && !movie.getDescription().isEmpty())
			return movieRepository.save(movie);
		else
			return null;
	}

	@Override
	public List<Movie> getMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie getMovie(Long id) {
		Optional<Movie> movie= movieRepository.findById(id);
		if(movie.isPresent()) {
			return movie.get();
		}
		return null;
	}

	@Override
	public Movie updateMovie(Long id, Movie movie,ImageModel imageModel) {
		Optional<Movie> movieResult=movieRepository.findById(id);
		if(movieResult.isPresent()) {
			if(!movie.getTittle().isEmpty() && !movie.getYear().isEmpty() && !movie.getDirector().isEmpty() && movie.getDuration()!=null && !movie.getDescription().isEmpty())
			{
			movieResult.get().setTittle(movie.getTittle());
			movieResult.get().setYear(movie.getYear());
			movieResult.get().setDirector(movie.getDirector());
			movieResult.get().setDuration(movie.getDuration());
			movieResult.get().setDescription(movie.getDescription());
			movieResult.get().setImageModel(imageModel);
			return movieRepository.save(movieResult.get());
			}
			else {
				return null;
			}
		}
		return null;
	}
	
	@Override
	public Boolean deleteMovie(Long id) {
		Optional<Movie> movie= movieRepository.findById(id);
		if(movie.isPresent()) {
			movieRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	
	
}
