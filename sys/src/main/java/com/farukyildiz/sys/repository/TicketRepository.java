package com.farukyildiz.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.farukyildiz.sys.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
	@Query("SELECT COUNT(t) FROM Ticket t WHERE t.saloon_id=?1 AND t.date=?2 AND t.session=?3")
	int ticketCount(Long saloon_id,String date,String session);
}
