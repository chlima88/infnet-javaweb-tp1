package br.edu.infnet.tpapp.repository;


import br.edu.infnet.tpapp.domain.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Optional;


@Repository
@FeignClient(url = "https://fakestoreapi.com/products", name = "FakeStoreAPI")
public interface ProductRepository extends IRepository<Product> {

    @GetMapping(value = "/{id}")
    Optional<Product> get(@PathVariable int id);

    @GetMapping
    Collection<Product> list();

    @PostMapping
    void add(Product product);

    @DeleteMapping(value = "/{id}")
    void remove(@PathVariable int id);
}
