package com.farukyildiz.sys.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farukyildiz.sys.entity.Saloon;
import com.farukyildiz.sys.entity.Ticket;
import com.farukyildiz.sys.repository.SaloonRepository;
import com.farukyildiz.sys.repository.TicketRepository;
import com.farukyildiz.sys.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;
	private SaloonRepository saloonRepository;
	
	
	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepository,SaloonRepository saloonRepository) {
		this.ticketRepository = ticketRepository;
		this.saloonRepository=saloonRepository;
	}

	@Override
	public Ticket createTicket(Ticket ticket) {
		Long id=ticket.getSaloon_id();
		String date=ticket.getDate();
		String session=ticket.getSession();
		if(!ticket.getEmail().isEmpty() && !ticket.getPhone().isEmpty() && ticket.getMovie_id()!=null && id!=null && !date.isEmpty() && !session.isEmpty()) {
			if(isAvaliable(id,date,session)) {
				return ticketRepository.save(ticket);
			}
			else {
				return null;
			}
		}
		else
			return null;
	}
	
	public boolean isAvaliable(Long id,String date,String session) {
		int count=ticketRepository.ticketCount(id, date, session);
		Optional<Saloon> saloon=saloonRepository.findById(id);
		if(saloon.isPresent()) {
			int avaliableForSaloon=saloon.get().getCapacity();
			if((count>=avaliableForSaloon-1) || (date.compareTo(saloon.get().getExpire_date())>0)) {
				return false;
			}
		}
		else {
			return false;
		}
		return true;
	}
	
	@Override
	public List<Ticket> getTickets() {
		List<Ticket> tickets=ticketRepository.findAll();
		return tickets;
	}

	@Override
	public Ticket getTicketById(Long id) {
		Optional<Ticket> ticket=ticketRepository.findById(id);
		if(ticket.isPresent()) {
			return ticket.get();
		}
		return null;
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		if(!ticket.getEmail().isEmpty() && !ticket.getPhone().isEmpty() && ticket.getMovie_id()!=null && !ticket.getDate().isEmpty()&& ticket.getSaloon_id()!=null && !ticket.getSession().isEmpty())
			{
			Ticket newTicket=new Ticket();
			newTicket.setEmail(ticket.getEmail());
			newTicket.setPhone(ticket.getPhone());
			newTicket.setMovie_id(ticket.getMovie_id());
			newTicket.setSaloon_id(ticket.getMovie_id());
			newTicket.setSession(ticket.getSession());
			return newTicket;
			}
		return null;
	}

	@Override
	public Boolean deleteTicket(Long id) {
		Optional<Ticket> ticket= ticketRepository.findById(id);
		if(ticket.isPresent()) {
			ticketRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
