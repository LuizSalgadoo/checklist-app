import { Component, OnInit } from '@angular/core';
import { Tarefa } from '../model/tarefa';
import { TarefasService } from '../services/tarefas.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css'],
})
export class TarefaComponent implements OnInit {
  tarefas: Observable<Tarefa[]>;
  displayedColumns = ['nome', 'prioridade', 'horamarcada', 'status', 'estadotarefa', 'acoes'];

  constructor(
    private tarefaService: TarefasService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.tarefas = this.tarefaService.listar();
  }

  ngOnInit(): void {}

  onAdd() {
    this.router.navigate(['criarcheklist'], { relativeTo: this.route });
  }

  onEdit(): void {
    this.router.navigate(['atualizar'], { relativeTo: this.route });
  }

  delete() {

  }

  alterarEstadoObjeto() {

  }

}
