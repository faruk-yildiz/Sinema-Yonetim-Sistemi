package com.farukyildiz.sys.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farukyildiz.sys.entity.Saloon;
import com.farukyildiz.sys.repository.SaloonRepository;
import com.farukyildiz.sys.service.SaloonService;

@Service
public class SaloonServiceImpl implements SaloonService{

	private SaloonRepository saloonRepository;
	
	@Autowired
	public SaloonServiceImpl(SaloonRepository saloonRepository) {
		this.saloonRepository = saloonRepository;
	}

	@Override
	public Saloon createSaloon(Saloon saloon) {
		Saloon saloonObj=new Saloon();
		if(!saloon.getName().isEmpty() && saloon.getCapacity()!=null && saloon.getMovie_id()!=null && !saloon.getExpire_date().isEmpty() && !saloon.getSessions().isEmpty())
		{	
			saloonObj.setName(saloon.getName());
			saloonObj.setCapacity(saloon.getCapacity());
			saloonObj.setMovie_id(saloon.getMovie_id());
			saloonObj.setExpire_date(saloon.getExpire_date());
			saloonObj.setSessions(saloon.getSessions());
			return saloonRepository.save(saloonObj);
		}
		return null;
	}

	@Override
	public List<Saloon> getSaloons() {
		return saloonRepository.findAll();
	}

	@Override
	public Saloon getSaloon(Long id) {
		Optional<Saloon> saloonOptional=saloonRepository.findById(id);
		if(saloonOptional.isPresent()) {
			return saloonOptional.get();
		}
		return null;
	}

	@Override
	public Saloon updateSaloon(Long id,Saloon saloon) {
		Optional<Saloon> saloonOptional=saloonRepository.findById(id);
		if(saloonOptional.isPresent()) {
			if(!saloon.getName().isEmpty() && saloon.getCapacity()!=null && saloon.getMovie_id()!=null && !saloon.getExpire_date().isEmpty() && !saloon.getSessions().isEmpty())
			{
			saloonOptional.get().setName(saloon.getName());
			saloonOptional.get().setCapacity(saloon.getCapacity());
			saloonOptional.get().setMovie_id(saloon.getMovie_id());
			saloonOptional.get().setExpire_date(saloon.getExpire_date());
			saloonOptional.get().setSessions(saloon.getSessions());
			return saloonRepository.save(saloonOptional.get());
			}
			else
				return null;
		}
		
		return null;
	}

	@Override
	public Boolean deleteSaloon(Long id) {
		Optional<Saloon> saloon= saloonRepository.findById(id);
		if(saloon.isPresent()) {
			saloonRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<Saloon> getSaloonsByMovieId(Long movie_id) {
		List<Saloon> saloons=saloonRepository.findByMovie_id(movie_id);
		return saloons;
	}
	
	public void checkExpiration() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		List<Saloon> saloons=saloonRepository.findAll();
		for (Saloon saloon : saloons) {
			if(saloon.getExpire_date().compareTo(now.format(dtf))<0)
			{
				saloonRepository.delete(saloon);
			}
		}
	}
	

}
