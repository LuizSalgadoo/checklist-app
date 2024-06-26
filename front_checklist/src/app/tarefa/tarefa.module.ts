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
import { MatMenuModule } from '@angular/material/menu';
import { FormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';



import { TarefaFormComponent } from './tarefa-form/tarefa-form.component';
import { TarefaRoutingModule } from './tarefa-routing.module';
import { TarefaUpdateComponent } from './tarefa-update/tarefa-update.component';
import { TarefaComponent } from './tarefa/tarefa.component';
import { TarefaDeleteComponent } from './tarefa-delete/tarefa-delete.component';
import { TarefaModalComponent } from './tarefa-modal/tarefa-modal.component';








@NgModule({
  declarations: [
    TarefaComponent,
    TarefaFormComponent,
    TarefaUpdateComponent,
    TarefaDeleteComponent,
    TarefaModalComponent
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
    MatListModule,
    MatMenuModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule
  ]
})
export class TarefaModule { }
