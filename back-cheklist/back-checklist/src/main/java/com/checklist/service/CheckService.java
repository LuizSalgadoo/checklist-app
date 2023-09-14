package com.checklist.service;

import com.checklist.dto.CheckListDtoGet;
import com.checklist.enums.Status;
import com.checklist.model.CheckList;
import com.checklist.repositories.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CheckService {

    @Autowired
    private CheckRepository checkRepository;

    public void atualizarStatus(Long check, Status novoStatus) {
        CheckList checkList = checkRepository.findById(check);


        checkList.setStatus(novoStatus);
        checkRepository.save(checkList);
    }

    public Status getStatus(Long checkId) {
        CheckList checkList = checkRepository.findById(checkId);


        return checkList.getStatus();
    }

    public CheckList alterarStatus(Integer id) {
        Optional<CheckList> optionalCheckList = checkRepository.findById(id);

        if (optionalCheckList.isPresent()) {
            CheckList checkList = optionalCheckList.get();
            Status statusAtual = checkList.getStatus();

            // Lógica para alternar o status
            switch (statusAtual) {
                case PENDENTE:
                    checkList.setStatus(Status.INICIADO);
                    break;
                case INICIADO:
                    checkList.setStatus(Status.FINALIZADO);
                    break;
                case FINALIZADO:
                    checkList.setStatus(Status.PENDENTE);
                    break;
            }

            // Salvar o CheckList atualizado no banco de dados
            checkRepository.save(checkList);

            return checkList;
        } else {
            throw new EntityNotFoundException("CheckList com ID " + id + " não encontrado");
        }
    }
}

