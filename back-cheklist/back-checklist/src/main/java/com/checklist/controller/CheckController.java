package com.checklist.controller;

import com.checklist.dto.CheckListDtoGet;
import com.checklist.dto.CheckListDtoPost;
import com.checklist.dto.CheckListDtoPut;
import com.checklist.enums.Prioridade;
import com.checklist.model.CheckList;
import com.checklist.repositories.CheckRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producao/checklist")
public class CheckController {

    @Autowired
    private CheckRepository checkRepository;

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
    public ResponseEntity<CheckListDtoPost> criarCheckList(@Valid @RequestBody CheckListDtoPost form, UriComponentsBuilder uriBuiler) {
        CheckList checkListPost = form.converter(checkRepository);
        checkRepository.save(checkListPost);

        URI uri = uriBuiler.path("/criarchecklist/{id}").buildAndExpand(checkListPost.getId()).toUri();
        return ResponseEntity.created(uri).body(new CheckListDtoPost(checkListPost));
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
}
