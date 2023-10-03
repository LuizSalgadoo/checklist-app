import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { TarefasService } from '../services/tarefas.service'; // Atualize com o caminho correto para o seu serviço
import {DiaDaSemana, DiasDaSemanaArray, Tarefa} from '../model/tarefa'; // Atualize com o caminho correto para o seu enum

type DiasDaSemanaGroup = {
  [key in string]?: FormControl;
};

@Component({
  selector: 'app-tarefa-form',
  templateUrl: './tarefa-form.component.html',
  styleUrls: ['./tarefa-form.component.css']
})
export class TarefaFormComponent implements OnInit {

  form: FormGroup;
  selectedOption: string | undefined;
  dias: string[] = DiasDaSemanaArray;


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
      referencia: [null, Validators.maxLength(30)],
      diasDaSemana: this.criarDiasDaSemanaArray(),
      repeticaoHoras: [null]
    });
  }


  ngOnInit(): void {
  }

  criarDiasDaSemanaArray(): FormArray {
    const controls: FormControl[] = this.dias.map(dia => new FormControl(false));
    return new FormArray(controls);
  }


  onSubmit() {
    const formData = this.form.value;
    const diasSelecionados: DiaDaSemana[] = [];

    Object.keys(formData.diasDaSemana).forEach((dia, index) => {
      if (formData.diasDaSemana[dia]) {
        diasSelecionados.push(index as unknown as DiaDaSemana);
      }
    });

    const tarefa: Tarefa = {
      ...formData,
      diasDaSemana: diasSelecionados
    };

    this.service.save(tarefa).subscribe(result => console.log(result));
    this.router.navigateByUrl('/');
  }


  onCancel() {
    this.router.navigateByUrl('/');
  }

  changeSelectedOption() {
    this.selectedOption = 'opcao2';
  }

  printValue(value: any) {
    console.log(value);
  }

}
