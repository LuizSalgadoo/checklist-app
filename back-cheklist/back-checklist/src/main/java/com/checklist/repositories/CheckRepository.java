package com.checklist.repositories;

import com.checklist.dto.CheckListDtoGet;
import com.checklist.enums.Status;
import com.checklist.model.CheckList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CheckRepository extends CrudRepository<CheckList, Integer>{

    CheckList findByNome(String nome);

    CheckList findById(UUID id);


    CheckList findById(Long checklistId);

    @Modifying
    @Query("UPDATE CheckList c SET c.status = 'PENDENTE', c.horainicio = null, c.horafim = null")
    void resetTasksStatusAndTimes();

    @Query("SELECT c FROM CheckList c WHERE c.status = :status AND c.horamarcada <= :horaAtual AND c.horainicio IS NULL")
    List<CheckList> findPendentesComHoraMarcadaPassada(@Param("status") Status status, @Param("horaAtual") String horaAtual);
}
