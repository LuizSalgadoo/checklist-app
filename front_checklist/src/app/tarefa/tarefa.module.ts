import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';

import { TarefaFormComponent } from './tarefa-form/tarefa-form.component';
import { TarefaRoutingModule } from './tarefa-routing.module';
import { TarefaUpdateComponent } from './tarefa-update/tarefa-update.component';
import { TarefaComponent } from './tarefa/tarefa.component';
import { TarefaDeleteComponent } from './tarefa-delete/tarefa-delete.component';








@NgModule({
  declarations: [
    TarefaComponent,
    TarefaFormComponent,
    TarefaUpdateComponent,
    TarefaDeleteComponent
  ],
  imports: [
    CommonModule,
    TarefaRoutingModule,
    MatTableModule,
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSidenavModule,
    MatListModule
  ]
})
export class TarefaModule { }
