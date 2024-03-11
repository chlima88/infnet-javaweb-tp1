package br.edu.infnet.tpapp.services;

import java.util.*;

import br.edu.infnet.tpapp.domain.model.Customer;
import br.edu.infnet.tpapp.repository.CustomerRepository;
import br.edu.infnet.tpapp.repository.GenericRepository;
import br.edu.infnet.tpapp.repository.IRepository;
import br.edu.infnet.tpapp.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTests {

    CustomerService sut;
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "Elberth", "0987654321", "em@ecomp.com", "1990-01-01");
        customer.setCreatedAt("2012-12-20");
        CustomerRepository customerRepository = new CustomerRepository();
        sut = new CustomerService(customerRepository);
    }

    @Test
    @DisplayName("Should create a customer")
    void shouldCreateACustomer() throws Exception {
        sut.add(customer);

        assertTrue(sut.list().contains(customer));
    }

    @Test
    @DisplayName("Should remove a customer")
    void shouldRemoveACustomer() throws Exception {
        sut.add(customer);
        sut.remove(customer.getId());

        assertTrue(sut.list().isEmpty());
    }

    @Test
    @DisplayName("Should retrieve a customer")
    void shouldRetrieveACustomer() throws Exception {
        sut.add(customer);

        assertEquals(customer, sut.get(customer.getId()));
    }

    @Test
    @DisplayName("Should retrieve a customer list")
    void shouldRetrieveACustomerList() throws Exception {
        sut.add(customer);
        Customer customer2 = new Customer(2, "Charles", "123", "cl@ecomp.com", "2000-11-11");
        sut.add(customer2);

        assertEquals(2,sut.list().size());
        assertEquals(customer, sut.get(customer.getId()));
        assertEquals(customer2, sut.get(customer2.getId()));
    }

    @Test
    @DisplayName("Should de able to activate an user")
    void shouldBeAbleToActivateAnUser() throws Exception {
        customer.activate();
        sut.add(customer);
        sut.activate(customer.getId());
    }

    @Test
    @DisplayName("Should de able to deactivate an user")
    void shouldBeAbleToDeactivateAnUser() throws Exception {
        customer.deactivate();
        sut.add(customer);
        sut.deactivate(customer.getId());

    }
}
