package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    private final String firstName = "Jim";
    private final String lastName = "Buck";
    private final Long id = 2L;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();

        CustomerDTO customer2 = new CustomerDTO();

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        //when
        when(customerService.getCustomers()).thenReturn(customers);

        //then

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));


    }



    @Test
    public void getCustomerById() throws Exception{
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        customerDTO.setFirstName(firstName);

        //when
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        //then
        mockMvc.perform(get("/api/v1/customers/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(firstName)));


    }


    @Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Alex");
        customer.setLastName("Semplinski");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setUrl("/api/v1/customers/1");

        //when
        when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

        //then
        mockMvc.perform(post("/api/v1/customers/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Alex")))
                .andExpect(jsonPath("$.url", equalTo("/api/v1/customers/1")));

    }


    @Test
    public void updateCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Alex");
        customer.setLastName("Semplinski");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setUrl("/api/v1/customers/1");

        //when
        when(customerService.saveCustomerById(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //then

        mockMvc.perform(put("/api/v1/customers/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Alex")))
                .andExpect(jsonPath("$.url", equalTo("/api/v1/customers/1")));

    }


}