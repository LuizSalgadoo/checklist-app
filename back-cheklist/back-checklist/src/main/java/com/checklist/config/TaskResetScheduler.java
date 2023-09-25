package com.checklist.config;

import com.checklist.model.CheckList;
import com.checklist.model.CheckListHistory;
import com.checklist.repositories.CheckRepository;
import com.checklist.repositories.CheckRepositoryHistory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;

@Component
public class TaskResetScheduler {

    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private CheckRepositoryHistory checkRepositoryHistory;

    @Scheduled(cron = "0 35 11 * * ?")
    @Transactional
    public void resetTasks() {
        try {
            // Salva os dados atuais na tabela de histórico
            Iterable<CheckList> currentTasks = checkRepository.findAll();
            for (CheckList task : currentTasks) {
                CheckListHistory history = new CheckListHistory();
                history.setNome(task.getNome());
                history.setHoramarcada(task.getHoramarcada());
                history.setHorainicio(task.getHorainicio());
                history.setHorafim(task.getHorafim());
                checkRepositoryHistory.save(history);
            }

            // Reset as tarefas
            checkRepository.resetTasksStatusAndTimes();
        } catch (Exception e) {
            e.printStackTrace(); // Considere usar um logger aqui
        }
    }
}
