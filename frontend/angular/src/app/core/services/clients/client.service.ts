import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Client } from '../../models/clients/client.model';
import { API_ROUTES } from '../../constants/api-routes';
import { handleError } from '../../../shared/components/error/generic-handler-error/error-handler';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = API_ROUTES.CLIENTS;

  constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl.GET_ALL).pipe(
      catchError(handleError)
    );
  }

  updateClient(id: number, client: Client): Observable<Client> {
    return this.http.put<Client>(this.apiUrl.UPDATE(id), client).pipe(
      catchError(handleError)
    );
  }

  deleteClient(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl.DELETE(id)).pipe(
      catchError(handleError)
    );
  }

  registerClient(client: { nombre: string }): Observable<Client> {
    return this.http.post<Client>(this.apiUrl.CREATE, client).pipe(
      catchError(handleError)
    );
  }
}