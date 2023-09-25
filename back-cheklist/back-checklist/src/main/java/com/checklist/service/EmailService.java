package com.checklist.service;

import com.checklist.model.CheckList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(List<CheckList> checkListsPendentes, LocalDateTime agora) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lvpsalgado1@outlook.com");
        message.setTo("lvpsalgado1@gmail.com");
        message.setSubject("Tarefas não iniciadas");

        StringBuilder texto = new StringBuilder("As seguintes tarefas não foram iniciadas:\n");
        for (CheckList checkList : checkListsPendentes) {
            LocalTime horaMarcada = LocalTime.parse(checkList.getHoramarcada(), DateTimeFormatter.ofPattern("HH:mm"));
            long atrasoMinutos = Duration.between(horaMarcada, agora.toLocalTime()).toMinutes();


            long horas = atrasoMinutos / 60;
            long minutos = atrasoMinutos % 60;
            String atrasoFormatado;
            if (horas > 0) {
                atrasoFormatado = String.format("%02d:%02d horas de atraso", horas, minutos);
            } else {
                atrasoFormatado = String.format("%d minutos de atraso", minutos);
            }

            texto.append("- ").append(checkList.getNome())
                    .append(" (").append(atrasoFormatado).append(")\n");
        }

        message.setText(texto.toString());
        mailSender.send(message);
    }
}
