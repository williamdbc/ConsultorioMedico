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
          this.handleCORSError(error);
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

  update(obj: any, rota: string, id: number){
    return this.http.put(`${this.API}${rota}/${id}`, obj).pipe(map((res: any) => {
      return res;
    }));
  }


  private handleCORSError(error: any): void {
    if (error.status === 0) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'CORS issue: Unable to connect to the server. Please check server configuration.',
        life: 6000 // Display for 6 seconds
      });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: `HTTP error ${error.status}: ${error.message}`,
        life: 6000 // Display for 6 seconds
      });
    }

  }
}
