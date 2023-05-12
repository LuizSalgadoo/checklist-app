import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TarefasService } from '../services/tarefas.service';
import { Tarefa } from '../model/tarefa';

@Component({
  selector: 'app-tarefa-update',
  templateUrl: './tarefa-update.component.html',
  styleUrls: ['./tarefa-update.component.css']
})
export class TarefaUpdateComponent implements OnInit {

    tarefa: Tarefa | undefined;
    form: FormGroup;
    selectedOption: string | undefined;

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

    updateTarefa(): void {
      this.tarefasService.update(this.form.value).subscribe(() => {
        this.router.navigateByUrl('/');
      });
    }

}
