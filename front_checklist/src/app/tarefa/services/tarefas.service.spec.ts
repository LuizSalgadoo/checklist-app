import { TarefasService } from "./tarefas.service";
import {TestBed} from "@angular/core/testing";
import {HttpClient} from "@angular/common/http";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";

describe('service: Tarefas', () => {
  let service: TarefasService;
  let httpController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TarefasService],
      imports: [HttpClientTestingModule]
    });

    service = TestBed.inject(TarefasService);
    httpController = TestBed.inject(HttpTestingController)
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  })

  it('return the tasks', () => {
    const taskResponse = [
      {
        id: 1,
        nome: "Teste1",
        prioridade: "ALTA",
        horamarcada: "23:23",
        referencia: null,
        status: "PENDENTE",
        horainicio: null,
        horafim: null
      },
      {
        id: 2,
        nome: "Lambda",
        prioridade: "NORMAL",
        horamarcada: "22:22",
        referencia: "Cálculo_lambda",
        status: "PENDENTE",
        horainicio: null,
        horafim: null
      }
    ];

    service.listar().subscribe(response => {
      expect(response.length).toEqual(2);
      expect(response[0].nome).toEqual('Teste1');
      expect(response[1].nome).toEqual('Lambda');
    });

    const req = httpController.expectOne('producao/checklist');
    expect(req.request.method).toBe('GET');

    req.flush(taskResponse);

  });

  afterEach(() => {
    httpController.verify();
  })

})
