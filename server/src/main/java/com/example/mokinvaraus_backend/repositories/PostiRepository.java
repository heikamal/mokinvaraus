package com.example.mokinvaraus_backend.repositories;

import com.example.mokinvaraus_backend.models.Posti;
import org.springframework.data.repository.CrudRepository;

public interface PostiRepository extends CrudRepository<Posti, String> {
}
