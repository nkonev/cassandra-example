package org.example;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, ShoppingCart.ShoppingCartKey> {

    Iterable<ShoppingCart> findByKeyUserid(String userId, Sort sort);
}
