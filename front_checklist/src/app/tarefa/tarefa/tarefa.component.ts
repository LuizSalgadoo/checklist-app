import { Component, OnInit } from '@angular/core';
import { Tarefa } from '../model/tarefa';
import { TarefasService } from '../services/tarefas.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { TarefaModalComponent } from '../tarefa-modal/tarefa-modal.component';

enum Status {
  Pendente = 'pendente',
  Iniciado = 'iniciado',
  Finalizado = 'finalizado'
}

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css'],
})
export class TarefaComponent implements OnInit {

  carregando = false

  tarefas: Observable<Tarefa[]>;
  displayedColumns = ['referencia', 'nome', 'prioridade', 'horamarcada', 'status', 'acoes'];
  currentStatus: Status = Status.Pendente;
  status: string = 'pendente';


  constructor(
    private tarefaService: TarefasService,
    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog
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

  openModalCritic() {
    const dialogRef = this.dialog.open(TarefaModalComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('O modal foi fechado', result);
    });
  }

  alterarStatus(element: any) {
    // Captura a data e hora atuais
    const  currentTime = new Date().toLocaleString();

    if (!element.horaInicio) {
      element.horaInicio = currentTime;
  } else {
      element.horaFim = currentTime;
  }

    // Faça a chamada ao serviço para alterar o status
    this.tarefaService.alterarStatus(element.id, element.horaInicio, element.horaFim).subscribe(
      (response) => {
        // Lógica para atualizar a interface do usuário com o novo status
        console.log('Status alterado com sucesso!', response);
        // Atualize element.status com o novo status se necessário
        element.status = response.status;

        // Atualize element.icon com base no novo status
        element.icon = this.obterIconePorStatus(response.status);

        // Log da hora em que o botão foi clicado
        console.log('Botão clicado em:', element.clickedAt);
      },
      (error) => {
        console.error('Erro ao alterar o status', error);
      }
    );
}

  obterIconePorStatus(status: string): string {
    switch (status.toLowerCase()) {
      case 'pendente':
        return 'pending_actions';
      case 'iniciado':
        return 'play_arrow';
      case 'finalizado':
        return 'done_all';
      default:
        return 'pending_actions'; // Ícone padrão
    }
  }

  construirUrl(referencia: string): string {
    return 'https://pt.wikipedia.org/wiki/' + referencia;
  }



}
