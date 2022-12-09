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

import com.farukyildiz.sys.entity.Ticket;
import com.farukyildiz.sys.service.TicketService;

@CrossOrigin
@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	
	private final TicketService ticketService;

	@Autowired
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
		
	@PostMapping
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
		Ticket resultTicket=ticketService.createTicket(ticket);
		if(resultTicket!=null)
			return ResponseEntity.ok(resultTicket);
		else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Ticket>> getTickets() {
		List<Ticket> tickets=ticketService.getTickets();
		return ResponseEntity.ok(tickets);
 	}
	
	@PutMapping(path ="/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable Long id,@RequestBody Ticket ticket)
	{
		Ticket resultTicket=ticketService.updateTicket(ticket);
		return ResponseEntity.ok(resultTicket);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Boolean> deleteTicket(@PathVariable Long id )
	{
		Boolean status=ticketService.deleteTicket(id);
		return ResponseEntity.ok(status);
	}
	
}
