import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Prestamo } from '../../models/prestamos/prestamo.model';
import { API_ROUTES } from '../../constants/api-routes';
import { handleError } from '../../../shared/components/error/generic-handler-error/error-handler';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {
  private apiUrl = API_ROUTES.LOANS;

  constructor(private http: HttpClient) {}

  getPrestamos(page: number, size: number = 4): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<any>(this.apiUrl.GET_ALL, { params }).pipe(
      catchError(handleError)
    );
  }

  filterPrestamos(nombreJuego: string, nombreCliente: string, fecha: string, page: number, size: number = 4): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);
    if (nombreJuego) {
      params = params.set('nombreJuego', nombreJuego);
    }
    if (nombreCliente) {
      params = params.set('nombreCliente', nombreCliente);
    }
    if (fecha) {
      params = params.set('fecha', fecha);
    }
    return this.http.get<any>(this.apiUrl.FILTER, { params }).pipe(
      catchError(handleError)
    );
  }

  createPrestamo(prestamo: Partial<Prestamo>): Observable<Prestamo> {
    return this.http.post<Prestamo>(this.apiUrl.CREATE, prestamo).pipe(
      catchError(handleError)
    );
  }

  deletePrestamo(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl.DELETE(id)).pipe(
      catchError(handleError)
    );
  }
}