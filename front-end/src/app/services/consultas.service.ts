import { HttpClient } from '@angular/common/http';
import { Injectable, Input } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Observable, catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultasService {
  private readonly API = '/api';
  constructor(private http: HttpClient, private messageService: MessageService) {
  }

  getAll(rota: string){
    return this.http.get<any[]>(`${this.API}` + rota)
    .pipe(map((res: any[]) => {
         console.log('res', res)
        let result = [...res];
        return result;
    }));
  }

  create(obj: any, rota: string): Observable<any> {
    return this.http.post(`${this.API}${rota}`, obj)
      .pipe(
        catchError(error => {
          this.handleError(error);
          return throwError(error);
        })
      );
  }

  getById(id: number, rota: string){
    return this.http.get(`${this.API}${rota}/${id}`).pipe(map((res: any) => {
      return res;
    }));

  }

  delete(id: number, rota: string){
    return this.http.delete(`${this.API}${rota}/${id}`);
  }

  update(obj: any, rota: string, id: number) {
    return this.http.put(`${this.API}/${rota}/${id}`, obj).pipe(
      map((res: any) => {
        return res;
      }),
      catchError(error => {
        this.handleError(error);
        return throwError(error);
      })
    );
  }

  findAgendamentoBetweenDates(rota: string, dataInicial: any, dataFinal: any){
    return this.http.get<any[]>(`${this.API}` + rota + '?startDateTime=' +  dataInicial + '&endDateTime=' + dataFinal)
    .pipe(map((res: any[]) => {
         console.log('res', res)
        let result = [...res];
        return result;
    }));
  }

  private handleError(error: any): Observable<never> {
    let errorMessage = 'Ocorreu um erro no serviço.';

    if (error.error) {
      // Erro do lado do cliente
      errorMessage = `Erro: ${error.error.message}`;
    }

    alert(errorMessage)
    // Exibir a mensagem de erro usando o MessageService

    return throwError(errorMessage);
  }

  findByNome(nome: string, rota: string): Observable<any> {
    console.log('rota', rota)
    return this.http.get(`${this.API}${rota}${nome}`).pipe(map((res: any) => {
      return res;
    }));

  }

  findConsultaByPaciente(cpf: any, birthDateString: any){
    return this.http.get<any[]>(`${this.API}/consultas/acessar`  + '?cpf=' +  cpf + '&birthDate=' + birthDateString)
    .pipe(map((res: any[]) => {
         console.log('res', res)
        let result = [...res];
        return result;
  }));
  }
}
