package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootStrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public BootStrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        List<Category> categories = new ArrayList<>();
        categories.add(fruits);
        categories.add(dried);
        categories.add(fresh);
        categories.add(exotic);
        categories.add(nuts);


        categoryRepository.saveAll(categories);

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Alex");
        customer1.setLastName("Semplinski");
        customer1.buildURL();

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Johnny");
        customer2.setLastName("Utah");
        customer2.buildURL();

        customerRepository.save(customer1);
        customerRepository.save(customer2);



        System.out.println("Data Loaded = "+ categoryRepository.count()+customerRepository.count());

    }
}
