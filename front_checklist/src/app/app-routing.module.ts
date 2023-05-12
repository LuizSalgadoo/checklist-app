import { Tarefa } from './tarefa/model/tarefa';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TarefaUpdateComponent } from './tarefa/tarefa-update/tarefa-update.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'tarefa'  },

  {
    path: 'tarefa',
    loadChildren: () => import('./tarefa/tarefa.module').then(m => m.TarefaModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
