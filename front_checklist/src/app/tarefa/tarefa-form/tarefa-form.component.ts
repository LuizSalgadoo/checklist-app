import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { TarefasService } from '../services/tarefas.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tarefa-form',
  templateUrl: './tarefa-form.component.html',
  styleUrls: ['./tarefa-form.component.css']
})
export class TarefaFormComponent implements OnInit {

  form: FormGroup;
  selectedOption: string | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private service: TarefasService,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: [null],
      prioridade: [null],
      horamarcada: [null],
    });
  }


  ngOnInit(): void {
  }

  onSubmit() {
    this.service.save(this.form.value).subscribe(result => console.log(result));
  }

  onCancel() {
    this.router.navigateByUrl('/');
  }

  changeSelectedOption() {
    this.selectedOption = 'opcao2';
  }

}
