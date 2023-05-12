import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Tarefa } from '../model/tarefa';
import { TarefasService } from '../services/tarefas.service';

@Component({
  selector: 'app-tarefa-delete',
  templateUrl: './tarefa-delete.component.html',
  styleUrls: ['./tarefa-delete.component.css']
})
export class TarefaDeleteComponent implements OnInit {

  form: FormGroup
  tarefa: Tarefa | undefined;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private tarefasService: TarefasService,
    private route : ActivatedRoute
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: [null],
      prioridade: [null],
      horamarcada: [null],
    });
   }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.tarefasService.readById(id).subscribe(tarefa => {
      this.tarefa = tarefa
      this.form.patchValue(tarefa)
    })
  }


  onCancel() {
    this.router.navigateByUrl('/');
  }

  confirmDelete() {
    const id = this.route.snapshot.paramMap.get('id');
    this.tarefasService.excluirTarefa(id).subscribe(() => {
      this.router.navigateByUrl('/')
    })
  }

}

