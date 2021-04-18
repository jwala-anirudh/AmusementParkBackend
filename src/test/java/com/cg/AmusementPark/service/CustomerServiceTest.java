package com.cg.AmusementPark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.AmusementPark.entities.Customer;
import com.cg.AmusementPark.exception.CustomerExistsException;
import com.cg.AmusementPark.exception.CustomerNotFoundException;
import com.cg.AmusementPark.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerService customerService;

	@Test
	void shouldReturnAllCustomers() throws CustomerNotFoundException {

		List<Customer> mockCustomers = new ArrayList<>();
		mockCustomers.add(new Customer(1, "anirudh", "anirudh@gmail.com", "anirudh123", "Hyderabad", "7981970397"));
		mockCustomers.add(new Customer(2, "bharath", "bharath@gmail.com", "bharath123", "Hyderabad", "7981970397"));
		mockCustomers.add(new Customer(3, "shubham", "shubham@gmail.com", "shubham123", "Pune", "7981970397"));

		Mockito.when(customerRepository.findAll()).thenReturn(mockCustomers);

		List<Customer> realCustomers = customerService.viewCustomers();

		Assert.assertEquals("anirudh", realCustomers.get(0).getUsername());

	}

	@Test
	void shouldReturnCustomerById() throws CustomerNotFoundException {

		List<Customer> mockCustomers = new ArrayList<>();
		mockCustomers.add(new Customer(1, "anirudh", "anirudh@gmail.com", "anirudh123", "Hyderabad", "7981970397"));
		mockCustomers.add(new Customer(2, "bharath", "bharath@gmail.com", "bharath123", "Hyderabad", "7981970397"));
		mockCustomers.add(new Customer(3, "shubham", "shubham@gmail.com", "shubham123", "Pune", "7981970397"));

		customerRepository.saveAll(mockCustomers);

		Mockito.when(customerRepository.findById(2)).thenReturn(
				Optional.of(new Customer(2, "bharath", "bharath@gmail.com", "bharath123", "Hyderabad", "7981970397")));

		Customer realCustomer = customerService.viewCustomer(2);

		Assert.assertEquals("bharath", realCustomer.getUsername());

	}

	@Test
	void shouldValidateCustomerByEmailAndPassword() throws CustomerNotFoundException {

		List<Customer> mockCustomers = new ArrayList<>();
		mockCustomers.add(new Customer(1, "anirudh", "anirudh@gmail.com", "anirudh123", "Hyderabad", "7981970397"));
		mockCustomers.add(new Customer(2, "bharath", "bharath@gmail.com", "bharath123", "Hyderabad", "7981970397"));
		mockCustomers.add(new Customer(3, "shubham", "shubham@gmail.com", "shubham123", "Pune", "7981970397"));

		customerRepository.saveAll(mockCustomers);

		Mockito.when(customerRepository.validateCustomer("anirudh@gmail.com", "anirudh123"))
				.thenReturn(new Customer(1, "anirudh", "anirudh@gmail.com", "anirudh123", "Hyderabad", "7981970397"));

		Customer realCustomer = customerService.validateCustomer("anirudh@gmail.com", "anirudh123");

		Assert.assertEquals("anirudh@gmail.com", realCustomer.getEmail());

	}

	@Test
	void shouldAddCustomer() throws CustomerNotFoundException, CustomerExistsException {

		Customer mockCustomer = new Customer(1, "anirudh", "anirudh@gmail.com", "anirudh123", "Hyderabad",
				"7981970397");

		Mockito.when(customerRepository.save(mockCustomer)).thenReturn(mockCustomer);

		Customer realCustomer = customerService.insertCustomer(
				new Customer(1, "anirudh", "anirudh@gmail.com", "anirudh123", "Hyderabad", "7981970397"));

		Assert.assertEquals("anirudh@gmail.com", realCustomer.getEmail());
		Assert.assertEquals("7981970397", realCustomer.getMobileNumber());
		Assert.assertEquals("Hyderabad", realCustomer.getAddress());

	}

	@Test
	void shouldUpdateCustomer() throws CustomerNotFoundException, CustomerExistsException {

		Customer mockCustomer = new Customer(1, "anirudh", "anirudh@gmail.com", "anirudh123", "Hyderabad",
				"7981970397");

		Mockito.when(customerRepository.save(mockCustomer)).thenReturn(mockCustomer);
		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(mockCustomer));

		customerService.insertCustomer(
				new Customer(1, "anirudh", "anirudh@gmail.com", "anirudh123", "Hyderabad", "7981970397"));

		Customer customerUpdated = customerService.findCustomerById(1);
		customerUpdated.setAddress("Mumbai");
		customerUpdated.setEmail("anirudh@capgemini.com");

		Assert.assertEquals("anirudh@capgemini.com", customerUpdated.getEmail());
		Assert.assertEquals("Mumbai", customerUpdated.getAddress());

	}

}