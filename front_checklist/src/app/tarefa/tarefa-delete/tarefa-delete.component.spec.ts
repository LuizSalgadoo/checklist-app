import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarefaDeleteComponent } from './tarefa-delete.component';

describe('TarefaDeleteComponent', () => {
  let component: TarefaDeleteComponent;
  let fixture: ComponentFixture<TarefaDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TarefaDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TarefaDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
