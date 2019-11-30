package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    private final String firstName = "Jim";
    private final String lastName = "Buck";
    private final Long id = 2L;

    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getCustomers() {

        //given
        Customer customer1 = new Customer();
        customer1.setFirstName(firstName);
        customer1.setLastName(lastName);

        Customer customer2 = new Customer();
        customer2.setFirstName("Alex");
        customer2.setLastName("Semplinski");

        //when

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getCustomers();
        //then
        assertEquals(2, customerDTOS.size());
    }

    @Test
    public void getCustomerById() {

        //given
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setId(id);

        when(customerRepository.findCustomerById(anyLong())).thenReturn(customer);

        //when

        CustomerDTO customerDTO = customerService.getCustomerById(id);



        //then
        assertEquals(firstName, customerDTO.getFirstName());


    }
}