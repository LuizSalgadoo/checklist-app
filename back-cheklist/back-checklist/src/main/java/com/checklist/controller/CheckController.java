package com.checklist.controller;

import com.checklist.dto.CheckListDtoGet;
import com.checklist.dto.CheckListDtoPost;
import com.checklist.dto.CheckListDtoPut;
import com.checklist.enums.Prioridade;
import com.checklist.enums.Status;
import com.checklist.model.CheckList;
import com.checklist.repositories.CheckRepository;
import com.checklist.service.CheckService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/producao/checklist")
public class CheckController {

    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private CheckService checkService;

    @GetMapping
    public List<CheckListDtoGet> getCheckList() {
        List<CheckList> checkList = (List<CheckList>) checkRepository.findAll();
        return CheckListDtoGet.converter(checkList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckListDtoGet> getCheckListId(@PathVariable Integer id) {
        Optional<CheckList> checkList = checkRepository.findById(id);
        if (checkList.isPresent()) {
            return ResponseEntity.ok(new CheckListDtoGet(checkList.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/criarchecklist")
    @PostMapping
    public ResponseEntity<List<CheckListDtoPost>> criarCheckList(@Valid @RequestBody CheckListDtoPost form, UriComponentsBuilder uriBuiler) {
        List<CheckList> checkLists = new ArrayList<>();
        List<CheckListDtoPost> responseDtos = new ArrayList<>();

        // Convertendo a hora marcada de String para LocalTime
        LocalTime horaMarcada = LocalTime.parse(form.getHoramarcada(), DateTimeFormatter.ofPattern("HH:mm"));

        int repeticoes = 24 / (form.getRepeticaoHoras() != null ? form.getRepeticaoHoras() : 24);

        LocalTime meiaNoite = LocalTime.parse("00:00");

        var repeticaoHoras = form.getRepeticaoHoras();
        System.out.println("Hora marcada: " + horaMarcada);
        System.out.println("Meia noite: " + meiaNoite);
        LocalTime calculoAteMeiaNoite = meiaNoite.minusHours(horaMarcada.getHour()).minusMinutes(horaMarcada.getMinute());
        System.out.println("Calculo está certo? " + calculoAteMeiaNoite);

        for (int i = 0; i < repeticoes; i++) {
            int horaAteMeiaNoite = calculoAteMeiaNoite.getHour();
            if (horaAteMeiaNoite / repeticaoHoras < i) {
                break;
            }


            LocalTime novaHora = horaMarcada.plusHours(i * (form.getRepeticaoHoras() != null ? form.getRepeticaoHoras() : 0));

            CheckList checkList = form.converter(checkRepository);
            checkList.setHoramarcada(novaHora.format(DateTimeFormatter.ofPattern("HH:mm"))); // Convertendo de volta para String
            checkLists.add(checkList);
        }

        checkRepository.saveAll(checkLists);

        for (CheckList checkList : checkLists) {
            URI uri = uriBuiler.path("/criarchecklist/{id}").buildAndExpand(checkList.getId()).toUri();
            responseDtos.add(new CheckListDtoPost(checkList, uri));
        }

        return ResponseEntity.created(null).body(responseDtos);
    }





    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> remove(@PathVariable Integer id) {
        checkRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Integer id, @RequestBody @Valid CheckListDtoPut form) {
        Optional<CheckList> checkListOptional = checkRepository.findById(id);
        CheckList checkList = new CheckList();
        BeanUtils.copyProperties(form, checkList);
        checkList.setId(checkListOptional.get().getId());
        checkList.setStatus(checkListOptional.get().getStatus());

        return ResponseEntity.ok().body(checkRepository.save(checkList));
    }



    @PutMapping("/{id}/alterar-status")
    public ResponseEntity<CheckList> alterarStatus(@PathVariable Integer id, @RequestBody CheckListDtoPut checkListDtoPut) {
        CheckList checkList = checkService.alterarStatus(id, checkListDtoPut.getHorainicio(), checkListDtoPut.getHorafim());
        return ResponseEntity.ok(checkList);
    }


}
