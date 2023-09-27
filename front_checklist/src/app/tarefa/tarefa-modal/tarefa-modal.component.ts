import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-tarefa-modal',
  templateUrl: './tarefa-modal.component.html',
  styleUrls: ['./tarefa-modal.component.css']
})
export class TarefaModalComponent implements OnInit {
  public evidenciaForm: FormGroup

  constructor(
    public fb: FormBuilder,
    public dialogRef: MatDialogRef<TarefaModalComponent>
  ) {}

  openModal() {
    console.log("oi")
  }

  ngOnInit(): void {

  }

  cancel(): void {
    this.dialogRef.close();
  }

}
