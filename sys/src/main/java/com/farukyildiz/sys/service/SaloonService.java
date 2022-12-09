package com.farukyildiz.sys.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.farukyildiz.sys.entity.Saloon;

@Service
public interface SaloonService {
	Saloon createSaloon(Saloon saloon);
	List<Saloon> getSaloons();
	Saloon getSaloon(Long id);
	Saloon updateSaloon(Long id,Saloon saloon);
	Boolean deleteSaloon(Long id);
	List<Saloon> getSaloonsByMovieId(Long movie_id);
	void checkExpiration();
}
