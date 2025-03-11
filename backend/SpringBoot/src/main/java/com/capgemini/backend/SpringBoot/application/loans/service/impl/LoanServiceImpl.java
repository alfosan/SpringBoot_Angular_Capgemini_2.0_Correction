package com.capgemini.backend.SpringBoot.application.loans.service.impl;

import com.capgemini.backend.SpringBoot.application.exceptions.prestamos.*;
import com.capgemini.backend.SpringBoot.application.loans.service.LoanService;
import com.capgemini.backend.SpringBoot.application.clients.service.ClienteService;
import com.capgemini.backend.SpringBoot.application.games.service.GameService;
import com.capgemini.backend.SpringBoot.application.loans.usecase.*;
import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import com.capgemini.backend.SpringBoot.domain.loans.repository.LoanRepository;
import com.capgemini.backend.SpringBoot.infraestructure.common.criteria.SearchCriteria;
import com.capgemini.backend.SpringBoot.infraestructure.loans.repository.LoanSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private GameService gameService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private GetAllLoans getAllLoans;

    @Autowired
    private GetLoanById getLoanById;

    @Autowired
    private CreateLoan createLoan;

    @Autowired
    private UpdateLoan updateLoan;

    @Autowired
    private DeleteLoan deleteLoan;

    @Override
    public List<Loan> getAllLoans() {
        return getAllLoans.execute();
    }

    @Override
    public Optional<Loan> getLoanById(Long id) {
        return getLoanById.execute(id);
    }

    @Override
    public Loan createLoan(Loan loan) {
        // Validar que el cliente y el juego existen
        if (!clienteExiste(loan.getNombreCliente())) {
            throw new ClienteNoReconocidoException("Cliente no reconocido: " + loan.getNombreCliente());
        }
        if (!juegoExiste(loan.getNombreJuego())) {
            throw new JuegoNoReconocidoException("Juego no reconocido: " + loan.getNombreJuego());
        }

        // Validar que la fecha de fin no es anterior a la fecha de inicio
        if (loan.getFechaDevolucion().isBefore(loan.getFechaCreacion())) {
            throw new FechaNoValidaException("La fecha de devolución no puede ser anterior a la fecha de creación");
        }

        // Validar que el periodo de préstamo no excede los 14 días
        long diasPrestamo = ChronoUnit.DAYS.between(loan.getFechaCreacion(), loan.getFechaDevolucion());
        if (diasPrestamo > 13) {
            throw new PrestamoMaximoException("El periodo de préstamo no puede exceder los 14 días");
        }

        // Validar que el mismo juego no está prestado a dos clientes distintos en el mismo día
        if (juegoPrestadoEnPeriodo(loan.getNombreJuego(), loan.getFechaCreacion(), loan.getFechaDevolucion())) {
            throw new FiltroFallidoException("El juego ya está prestado en el periodo especificado");
        }

        // Validar que el mismo cliente no tiene más de 2 juegos prestados en el mismo día
        if (clienteConMasDeDosPrestamos(loan.getNombreCliente(), loan.getFechaCreacion(), loan.getFechaDevolucion())) {
            throw new PrestamoMaximoException("El cliente no puede tener más de 2 juegos prestados en el mismo día");
        }

        return createLoan.execute(loan);
    }

    @Override
    public Loan updateLoan(Long id, Loan loan) {
        return updateLoan.execute(id, loan);
    }

    @Override
    public boolean deleteLoan(Long id) {
        return deleteLoan.execute(id);
    }

    @Override
    public Page<Loan> findByFilters(String nombreJuego, String nombreCliente, LocalDate fecha, Pageable pageable) {
        Specification<Loan> spec = Specification.where(null);

        if (nombreJuego != null && !nombreJuego.isEmpty()) {
            spec = spec.and(new LoanSpecification(new SearchCriteria("nombreJuego", ":", nombreJuego)));
        }
        if (nombreCliente != null && !nombreCliente.isEmpty()) {
            spec = spec.and(new LoanSpecification(new SearchCriteria("nombreCliente", ":", nombreCliente)));
        }
        if (fecha != null) {
            spec = spec.and(new LoanSpecification(new SearchCriteria("fechaCreacion", "<=", fecha)));
            spec = spec.and(new LoanSpecification(new SearchCriteria("fechaDevolucion", ">=", fecha)));
        }

        Page<Loan> loans = loanRepository.findAll(spec, pageable);

        if (loans.isEmpty()) {
            throw new FiltroFallidoException("No se encontraron préstamos con los filtros especificados");
        }

        return loans;
    }

    private boolean clienteExiste(String nombreCliente) {
        return clienteService.getAllClientes().stream()
                .anyMatch(cliente -> cliente.getNombre().equals(nombreCliente));
    }

    private boolean juegoExiste(String nombreJuego) {
        return gameService.getAllGames().stream()
                .anyMatch(game -> game.getTitulo().equals(nombreJuego));
    }

    private boolean juegoPrestadoEnPeriodo(String nombreJuego, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Loan> loans = loanRepository.findByNombreJuegoContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
                nombreJuego, fechaFin, fechaInicio);
        return !loans.isEmpty();
    }

    private boolean clienteConMasDeDosPrestamos(String nombreCliente, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Loan> loans = loanRepository.findByNombreClienteContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
                nombreCliente, fechaFin, fechaInicio);
        return loans.size() > 1;
    }
}