package com.cg.AmusementPark.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.AmusementPark.entities.TicketBooking;
import com.cg.AmusementPark.exception.CustomerNotFoundException;
import com.cg.AmusementPark.exception.TicketBookingNotFoundException;
import com.cg.AmusementPark.service.TicketBookingService;

@RestController
public class TicketBookingController {

	@Autowired
	private TicketBookingService ticketBookingService;

	@PostMapping(path = "/ticket")
	public TicketBooking insertTicketBooking(@Valid @RequestBody TicketBooking ticketBooking,
			BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new Exception("Ticking booking details are not valid");
		}

		return ticketBookingService.insertTicketBooking(ticketBooking);

	}

	@PutMapping(path = "/ticket")
	public TicketBooking updateTicketBooking(TicketBooking ticketBooking) throws TicketBookingNotFoundException {

		TicketBooking ticketBookingToUpdate = ticketBookingService.updateTicketBooking(ticketBooking);

		if (ticketBookingToUpdate == null) {
			TicketBookingNotFoundException ticketBookingException = new TicketBookingNotFoundException(
					"Ticket Booking you are trying to update is not found or invalid");
			throw ticketBookingException;
		}

		return ticketBookingToUpdate;

	}

	@DeleteMapping(path = "/ticket")
	public TicketBooking deleteTicketBooking(int ticketId) throws TicketBookingNotFoundException {

		TicketBooking ticketBookingToDelete = ticketBookingService.deleteTicketBooking(ticketId);

		if (ticketBookingToDelete == null) {
			TicketBookingNotFoundException ticketBookingException = new TicketBookingNotFoundException(
					"Ticket Booking you are trying to delete is not found or invalid");
			throw ticketBookingException;
		}

		return ticketBookingToDelete;

	}

	@GetMapping(path = "/ticket/{id}")
	public List<TicketBooking> viewAllTicketsOfCustomer(@PathVariable("id") int customerId)
			throws CustomerNotFoundException {

		List<TicketBooking> ticketBooking = ticketBookingService.viewAllTicketsOfCustomer(customerId);

		if (ticketBooking == null) {
			CustomerNotFoundException customerException = new CustomerNotFoundException(
					"No customer is found for specified ID");
			throw customerException;
		}

		return ticketBooking;

	}

	@GetMapping(path = "/ticket/bill/{id}")
	public float calculateBill(@PathVariable("id") int customerId) throws CustomerNotFoundException {
		float charges = ticketBookingService.calculateBill(customerId);

		if (charges == 0.0f) {
			CustomerNotFoundException customerException = new CustomerNotFoundException(
					"No customer is found for specified ID");
			throw customerException;
		}

		return charges;
	}

}
