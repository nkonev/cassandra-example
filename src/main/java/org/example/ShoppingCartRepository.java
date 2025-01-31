package org.example;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, ShoppingCart.ShoppingCartKey> {
}
