import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { first, tap, Observable } from 'rxjs';
import { Tarefa } from '../model/tarefa';
import { Printona } from '../model/printona';

@Injectable({
  providedIn: 'root',
})
export class TarefasService {
  private readonly API = 'producao/checklist';

  constructor(private httpClient: HttpClient) {}

  listar() {
    return this.httpClient.get<Tarefa[]>(this.API).pipe(
      first(),
      tap((tarefa) => console.log(tarefa))
    );
  }

  enviarPrint(print: any) {
    return this.httpClient
      .post<Printona>(this.API + '/printEvidencia', print)
      .pipe(first());
  }

  save(tarefa: Tarefa) {
    return this.httpClient
      .post<Tarefa>(this.API + '/criarchecklist', tarefa)
      .pipe(first());
  }

  readById(id: any): Observable<Tarefa> {
    const url = `${this.API}/${id}`;
    return this.httpClient.get<Tarefa>(url).pipe(first());
  }

  update(tarefa: Tarefa): Observable<Tarefa> {
    const url = `${this.API}/atualizar/${tarefa.id}`;
    return this.httpClient.put<Tarefa>(url, tarefa).pipe(first());
  }

  excluirTarefa(id: any): Observable<Tarefa> {
    const url = `${this.API}/deletar/${id}`;
    return this.httpClient.delete<Tarefa>(url).pipe(first());
  }

  alterarStatus(
    id: number,
    horaInicio: string,
    horaFim: string
  ): Observable<any> {
    const url = `${this.API}/${id}/alterar-status`;
    return this.httpClient.put(url, { horaInicio, horaFim });
  }
}
