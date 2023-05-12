import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarefaUpdateComponent } from './tarefa-update.component';

describe('TarefaUpdateComponent', () => {
  let component: TarefaUpdateComponent;
  let fixture: ComponentFixture<TarefaUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TarefaUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TarefaUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
