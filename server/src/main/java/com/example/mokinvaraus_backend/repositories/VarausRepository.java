package com.example.mokinvaraus_backend.repositories;

import com.example.mokinvaraus_backend.models.Varaus;
import org.springframework.data.repository.CrudRepository;

public interface VarausRepository extends CrudRepository<Varaus, Integer> {
}
