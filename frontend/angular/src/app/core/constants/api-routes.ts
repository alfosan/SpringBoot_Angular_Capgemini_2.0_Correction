import { environment } from '../../../enviroments/environment';

export const API_ROUTES = {
    CLIENTS: {
        GET_ALL: `${environment.apiUrl}/clientes`,
        CREATE: `${environment.apiUrl}/clientes`,
        UPDATE: (clientId: number) => `${environment.apiUrl}/clientes/${clientId}`,
        DELETE: (clientId: number) => `${environment.apiUrl}/clientes/${clientId}`,
    },
    GAMES: {
        GET_ALL: `${environment.apiUrl}/games`,
        GET_ONE: (gameId: number) => `${environment.apiUrl}/games/${gameId}`,
        CREATE: `${environment.apiUrl}/games`,
        UPDATE: (gameId: number) => `${environment.apiUrl}/games/${gameId}`,
        DELETE: (gameId: number) => `${environment.apiUrl}/games/${gameId}`,
    },
    LOANS: {
        GET_ALL: `${environment.apiUrl}/loans`,
        GET_ONE: (loanId: number) => `${environment.apiUrl}/loans/${loanId}`,
        FILTER: `${environment.apiUrl}/loans/filter`,
        CREATE: `${environment.apiUrl}/loans`,
        DELETE: (loanId: number) => `${environment.apiUrl}/loans/${loanId}`,
    }
};