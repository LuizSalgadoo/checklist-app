import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarefaModalComponent } from './tarefa-modal.component';

describe('TarefaModalComponent', () => {
  let component: TarefaModalComponent;
  let fixture: ComponentFixture<TarefaModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TarefaModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TarefaModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
