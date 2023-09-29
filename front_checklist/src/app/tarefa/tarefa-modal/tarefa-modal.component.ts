import { Component, OnInit, inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-tarefa-modal',
  templateUrl: './tarefa-modal.component.html',
  styleUrls: ['./tarefa-modal.component.css']
})
export class TarefaModalComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<TarefaModalComponent>
  ) {}

  ngOnInit(): void {

  }

  cancel(): void {
    this.dialogRef.close();
  }

}
