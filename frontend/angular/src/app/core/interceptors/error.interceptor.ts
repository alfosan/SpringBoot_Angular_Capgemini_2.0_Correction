import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        console.log('Interceptor caught an error:', error); // Agrega este log
        let errorMessage = '';

        if (error.error instanceof ErrorEvent) {
          // Client-side or network error
          errorMessage = `Error de conexión: Por favor, verifica tu conexión a internet y vuelve a intentarlo`;
        } else {
          // Backend error
          switch (error.status) {
            case 400:
              errorMessage = 'La solicitud enviada es incorrecta. Por favor, verifica los datos ingresados';
              break;
            case 401:
              errorMessage = 'No tienes autorización para acceder. Por favor, inicia sesión nuevamente';
              break;
            case 403:
              errorMessage = 'No tienes permisos suficientes para acceder a este recurso';
              break;
            case 404:
              errorMessage = 'El recurso que estás buscando no existe o ha sido movido';
              break;
            case 409:
              errorMessage = 'No es posible realizar el préstamo. Has alcanzado el límite máximo de 2 préstamos activos';
              break;
            case 500:
              errorMessage = 'Ha ocurrido un error en el servidor. Por favor, intenta más tarde o contacta al administrador';
              break;
            default:
              errorMessage = `Error inesperado (${error.status}): Por favor, intenta nuevamente o contacta soporte técnico`;
          }
        }

        Swal.fire({
          icon: 'error',
          title: `Error ${error.status}`,
          text: errorMessage,
          confirmButtonText: 'Cerrar'
        });

        console.error('Error HTTP:', error);

        return throwError(() => new Error(errorMessage));
      })
    );
  }
}