package com.farukyildiz.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.farukyildiz.sys.entity.Saloon;
import com.farukyildiz.sys.service.SaloonService;

@CrossOrigin
@RestController
@RequestMapping("/api/saloons")
public class SaloonController {

	
	private final SaloonService saloonService;

	@Autowired
	public SaloonController(SaloonService saloonService) {
		this.saloonService = saloonService;
	}

	@PostMapping
	public ResponseEntity<Saloon> createSaloon(@RequestBody Saloon saloon) {
		Saloon saloon2=new Saloon();
		saloon2=saloonService.createSaloon(saloon);
		if(saloon2!=null)
			return ResponseEntity.ok(saloon2);
		else
			return ResponseEntity.badRequest().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Saloon>> getSaloons(){
		saloonService.checkExpiration();
		List<Saloon> saloons=saloonService.getSaloons();
		return ResponseEntity.ok(saloons);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Saloon> getSaloon(@PathVariable("id") Long id){
		Saloon saloon=saloonService.getSaloon(id);
		return ResponseEntity.ok(saloon);
	}
	
	@GetMapping("findbymovie/{movie_id}")
	public ResponseEntity<List<Saloon>> getSaloonsByMovieId(@PathVariable("movie_id") Long movie_id){
		List<Saloon> saloons=saloonService.getSaloonsByMovieId(movie_id);
		return ResponseEntity.ok(saloons);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Saloon> editSaloon(@PathVariable("id") Long id,@RequestBody Saloon saloon){
		Saloon resultSaloon=new Saloon();
		resultSaloon=saloonService.updateSaloon(id, saloon);
		if(resultSaloon!=null)
			return ResponseEntity.ok(resultSaloon);
		else
			return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteMovie(@PathVariable("id") Long id) {
		Boolean status=saloonService.deleteSaloon(id);
		return ResponseEntity.ok(status);
	}
}
