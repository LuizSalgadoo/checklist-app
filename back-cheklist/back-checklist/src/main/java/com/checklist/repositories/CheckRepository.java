package com.checklist.repositories;

import com.checklist.dto.CheckListDtoGet;
import com.checklist.model.CheckList;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CheckRepository extends CrudRepository<CheckList, Integer>{

    CheckList findByNome(String nome);

    CheckList findById(UUID id);


    CheckList findById(Long checklistId);
}
