package com.checklist.repositories;

import com.checklist.model.CheckList;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CheckRepository extends CrudRepository<CheckList, Integer>{

    CheckList findByNome(String nome);


}
