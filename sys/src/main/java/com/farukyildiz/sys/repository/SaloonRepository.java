package com.farukyildiz.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.farukyildiz.sys.entity.Saloon;

public interface SaloonRepository extends JpaRepository<Saloon, Long>{
	@Query("SELECT s FROM Saloon s WHERE s.movie_id= ?1")
	List<Saloon> findByMovie_id(Long movie_id);
}
