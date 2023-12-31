package com.example.mokinvaraus_backend.repositories;

import com.example.mokinvaraus_backend.models.Palvelu;
import org.springframework.data.repository.CrudRepository;

public interface PalveluRepository extends CrudRepository<Palvelu, Integer> {
}
