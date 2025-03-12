import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';

export function handleError(error: HttpErrorResponse) {
  let errorMessage = 'An unknown error occurred!';
  if (error.error instanceof ErrorEvent) {
    // Client-side or network error
    errorMessage = `Error: ${error.error.message}`;
  } else {
    // Backend error
    errorMessage = error.error.message || `Error ${error.status}: ${error.statusText}`;
  }
  return throwError(errorMessage);
}