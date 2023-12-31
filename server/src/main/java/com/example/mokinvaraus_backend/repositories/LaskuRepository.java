package com.example.mokinvaraus_backend.repositories;

import com.example.mokinvaraus_backend.models.Lasku;
import org.springframework.data.repository.CrudRepository;

public interface LaskuRepository extends CrudRepository<Lasku, Integer> {
}
