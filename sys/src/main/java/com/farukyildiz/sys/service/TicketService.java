package com.farukyildiz.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farukyildiz.sys.entity.Ticket;

@Service
public interface TicketService {
	Ticket createTicket(Ticket ticket);
	List<Ticket> getTickets();
	Ticket getTicketById(Long id);
	Ticket updateTicket(Ticket ticket);
	Boolean deleteTicket(Long id);
	boolean isAvaliable(Long id,String date,String session);
	
}
