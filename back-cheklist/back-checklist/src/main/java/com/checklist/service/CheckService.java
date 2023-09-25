package com.checklist.service;

import com.checklist.enums.Status;
import com.checklist.model.CheckList;
import com.checklist.repositories.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CheckService {

    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private EmailService emailService;

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


            if (statusAtual == Status.FINALIZADO) {
                throw new IllegalStateException("Não é possível alterar atividades já finalizadas");
            }

            LocalDateTime agora = LocalDateTime.now();


            switch (statusAtual) {
                case PENDENTE:
                    checkList.setStatus(Status.INICIADO);
                    checkList.setHorainicio(agora);
                    break;
                case INICIADO:
                    checkList.setStatus(Status.FINALIZADO);
                    checkList.setHorafim(agora);
                    break;

            }


            checkRepository.save(checkList);

            return checkList;
        } else {
            throw new EntityNotFoundException("CheckList com ID " + id + " não encontrado");
        }
    }

    @Scheduled(fixedRate = 600000)
    public void verificarCheckListsPendentes() {
        LocalDateTime agora = LocalDateTime.now();
        String horaAtual = agora.format(DateTimeFormatter.ofPattern("HH:mm"));
        List<CheckList> checkListsPendentes = checkRepository.findPendentesComHoraMarcadaPassada(Status.PENDENTE, horaAtual);

        if (!checkListsPendentes.isEmpty()) {
            emailService.enviarEmail(checkListsPendentes, agora);
        }
    }
}

