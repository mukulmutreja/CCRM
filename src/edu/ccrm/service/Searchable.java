package ccrm.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Searchable<T> {

    // Search items by some condition
    List<T> search(Predicate<T> condition);

    // Get all items
    List<T> findAll();

    // Find one item by id
    Optional<T> findById(String id);
}
