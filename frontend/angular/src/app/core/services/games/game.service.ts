import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Game } from '../../models/games/game.model';
import { API_ROUTES } from '../../constants/api-routes';
import { handleError } from '../../../shared/components/error/generic-handler-error/error-handler';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiUrl = API_ROUTES.GAMES;

  constructor(private http: HttpClient) {}

  getGames(): Observable<Game[]> {
    return this.http.get<Game[]>(this.apiUrl.GET_ALL).pipe(
      catchError(handleError)
    );
  }

  updateGame(id: number, game: Game): Observable<Game> {
    return this.http.put<Game>(this.apiUrl.UPDATE(id), game).pipe(
      catchError(handleError)
    );
  }

  deleteGame(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl.DELETE(id)).pipe(
      catchError(handleError)
    );
  }

  registerGame(game: { titulo: string }): Observable<Game> {
    return this.http.post<Game>(this.apiUrl.CREATE, game).pipe(
      catchError(handleError)
    );
  }
}