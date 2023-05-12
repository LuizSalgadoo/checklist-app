import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TarefaComponent } from './tarefa/tarefa.component';
import { TarefaFormComponent } from './tarefa-form/tarefa-form.component';
import { TarefaUpdateComponent } from './tarefa-update/tarefa-update.component';
import { TarefaDeleteComponent } from './tarefa-delete/tarefa-delete.component';


const routes: Routes = [
  { path: '', component: TarefaComponent },
  { path: 'criarcheklist', component: TarefaFormComponent },
  { path: 'atualizar/:id', component: TarefaUpdateComponent },
  { path: 'deletar/:id', component: TarefaDeleteComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TarefaRoutingModule { }
