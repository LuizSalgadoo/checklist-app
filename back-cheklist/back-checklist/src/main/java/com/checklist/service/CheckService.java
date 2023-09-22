package com.checklist.service;

import com.checklist.enums.Status;
import com.checklist.model.CheckList;
import com.checklist.repositories.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
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

    public CheckList alterarStatus(Integer id, LocalDateTime horainicio, LocalDateTime horafim) {
        Optional<CheckList> optionalCheckList = checkRepository.findById(id);

        if (optionalCheckList.isPresent()) {
            CheckList checkList = optionalCheckList.get();
            Status statusAtual = checkList.getStatus();
            LocalDateTime agora = LocalDateTime.now();

            // Lógica para alternar o status e registrar a hora de início e fim
            switch (statusAtual) {
                case PENDENTE:
                    checkList.setStatus(Status.INICIADO);
                    checkList.setHorainicio(agora); // Registra a hora de início
                    break;
                case INICIADO:
                    checkList.setStatus(Status.FINALIZADO);
                    checkList.setHorafim(agora); // Registra a hora de fim
                    break;
                case FINALIZADO:
                    checkList.setStatus(Status.PENDENTE);
                    // Se necessário, resetar as horas de início e fim
                    checkList.setHorainicio(null);
                    checkList.setHorafim(null);
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

