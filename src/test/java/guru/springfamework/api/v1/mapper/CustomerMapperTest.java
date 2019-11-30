package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    private final String firstName = "Joe";
    private final String lastName = "Buck";

    private final Long id = 1L;

    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;


    @Test
    public void customerToCustomerDTO() {

        //given
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setId(1L);
        //when

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then

        assertEquals(id, customerDTO.getId());
        assertEquals(firstName, customerDTO.getFirstName());

    }
}