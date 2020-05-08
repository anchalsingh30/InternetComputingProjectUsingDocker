package com.pace.university.internet.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableAutoConfiguration
@RestController // it is used to create RESTful Web Services.
public class Controller {

	@Autowired
	private ObjectMapper objectMapper;

	private static final String CUSTOMER = "customers";
	private static final String ORDER = "order";

	@Value("classpath:database.json")
	private Resource resourceFile;

	/**
	 * This method extracts all customers information
	 * 
	 * @return list of all customers in json format
	 * @throws IOException
	 */
	@GetMapping("/customers")
	public JsonNode getCustomers() throws IOException {
		JsonNode jsonNode = readFile();
		return jsonNode.get(CUSTOMER);
	}

	/**
	 * This method extracts a particular customer information based on it's unique
	 * id
	 * 
	 * @param id unique identifier for customer
	 * @return single customer details
	 * @throws IOException
	 */
	@GetMapping("/customers/{id}")
	public JsonNode getCustomersWithId(@PathVariable int id) throws IOException {
		JsonNode jsonNode = readFile();
		return getCustomerWithIdHelper(id, jsonNode);
	}

	/**
	 * This method extracts order information of a particular customer
	 * 
	 * @param id unique identifier for customer
	 * @return list of all orders in json format
	 * @throws IOException
	 */
	@GetMapping("customers/{id}/orders")
	public JsonNode getOrder(@PathVariable int id) throws IOException {
		JsonNode jsonNode = readFile();
		JsonNode result = getCustomerWithIdHelper(id, jsonNode);
		return result == null ? null : result.get(ORDER);
	}

	/**
	 * This method extracts single order details based on it's order id
	 * 
	 * @param id      unique identifier for customer
	 * @param orderId unique identifier for order
	 * @return single order detail
	 * @throws IOException
	 */
	@GetMapping("customers/{id}/orders/{orderId}")
	public JsonNode getOrderId(@PathVariable int id, @PathVariable int orderId) throws IOException {
		JsonNode jsonNode = readFile();
		JsonNode result = getCustomerWithIdHelper(id, jsonNode);
		boolean found = false;
		if (result != null) {
			JsonNode temp = result.get(ORDER);
			for (JsonNode node : temp) {
				if (node.get("id").toString().replace("\"", "").equals(Integer.toString(orderId))) {
					result = node;
					found = true;
					break;
				}
			}
		}
		return found ? result : null;

	}

	/**
	 * This method reads the database.json file and returns the content of the file
	 * in a JsonNode format.
	 * 
	 * @return JsonNode containing the content of the file
	 * @throws IOException
	 */
	private JsonNode readFile() throws IOException {
		return objectMapper.readTree(resourceFile.getInputStream());
	}

	/**
	 * This method checks if the id is present in the database and if it exists then
	 * it returns the details, otherwise returns {@code null}
	 * 
	 * @param id       unique identifier for customer
	 * @param jsonNode customer information
	 * @return
	 */
	private JsonNode getCustomerWithIdHelper(int id, JsonNode jsonNode) {
		JsonNode result = null;
		for (JsonNode node : jsonNode.get(CUSTOMER)) {
			if (node.get("id").toString().replace("\"", "").equals(Integer.toString(id))) {
				result = node;
			}
		}
		return result;
	}

}
