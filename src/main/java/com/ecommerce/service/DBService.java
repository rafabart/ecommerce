package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.entity.enums.StatusPayment;
import com.ecommerce.entity.enums.TypeCustomer;
import com.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DBService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ItemPurchaseRepository itemPurchaseRepository;


    public void instantiateTestDatabase() {

        final State state1 = new State(null, "São Paulo", null);
        final State state2 = new State(null, "Rio de Janeiro", null);
        final State state3 = new State(null, "Minas Gerais", null);
        final State state4 = new State(null, "Amazonas", null);

        stateRepository.saveAll(Arrays.asList(state1, state2, state3, state4));

        final City city1 = new City(null, "São Paulo", state1);
        final City city2 = new City(null, "Araraquara", state1);
        final City city3 = new City(null, "Paraty", state2);
        final City city4 = new City(null, "Belo Horizonte", state3);
        final City city5 = new City(null, "Manaus", state4);

        cityRepository.saveAll(Arrays.asList(city1, city2, city3, city4, city5));

        Category category1 = new Category(null, "Informática", null);
        Category category2 = new Category(null, "Escritório", null);
        Category category3 = new Category(null, "Ferramentas", null);

        Product product1 = new Product(null, "Notebook", 1900.00, Arrays.asList(category1), null);
        Product product2 = new Product(null, "Impressora HP", 250.00, Arrays.asList(category1, category2), null);
        Product product3 = new Product(null, "Cadeira", 1900.00, Arrays.asList(category2), null);
        Product product4 = new Product(null, "Mesa", 1900.00, Arrays.asList(category2), null);
        Product product5 = new Product(null, "Alicate", 1900.00, Arrays.asList(category3), null);
        Product product6 = new Product(null, "Testador De Cabo De Rede RJ45", 37.00, Arrays.asList(category1, category3), null);

        category1.setProducts(Arrays.asList(product1, product2, product6));
        category2.setProducts(Arrays.asList(product2, product3, product4));
        category3.setProducts(Arrays.asList(product5, product6));

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6));

        Set<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add("1699998888");
        phoneNumbers.add("1698885555");

        Customer customer = new Customer();
        customer.setName("Rafael Marinho");
        customer.setTypeCustomer(TypeCustomer.NATURALPERSON);
        customer.setCpfOrCnpj("58817217050");
        customer.setEmail("teste@test.com");
        customer.setPhoneNumbers(phoneNumbers);

        city2.setId(2L);

        Address address = new Address(null, "Rua sete de setembro", "155", "Centro", null, "14920-000", customer, city2);

        customer.getAddresses().add(address);

        customer = customerRepository.save(customer);

        Purchase purchase = new Purchase();
        purchase.setDate(new Date());
        purchase.setCustomer(customer);
        purchase.setAddress(customer.getAddresses().get(0));

        CreditCard payment = new CreditCard();
        payment.setStatusPayment(StatusPayment.PENDING);
        payment.setInstallments(10);

        purchase.setPayment(payment);

        payment.setPurchase(purchase);

        ItemPurchase itemPurchase = new ItemPurchase();
        itemPurchase.setPurchase(purchase);
        product1.setId(1L);
        itemPurchase.setProduct(product1);
        itemPurchase.setPrice(product1.getPrice());
        itemPurchase.setDiscount(0.0);
        itemPurchase.setQuantity(5);

        purchaseRepository.save(purchase);

        itemPurchaseRepository.save(itemPurchase);
    }
}
