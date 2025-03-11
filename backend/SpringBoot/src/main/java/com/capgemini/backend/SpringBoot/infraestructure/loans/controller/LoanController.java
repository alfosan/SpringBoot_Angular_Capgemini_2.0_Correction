package com.capgemini.backend.SpringBoot.infraestructure.loans.controller;

import com.capgemini.backend.SpringBoot.application.dto.loans.LoanDTO;
import com.capgemini.backend.SpringBoot.application.loans.service.LoanService;
import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import com.capgemini.backend.SpringBoot.infraestructure.loans.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans().stream()
                .map(LoanMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanService.getLoanById(id);
        return loan.map(l -> ResponseEntity.ok(LoanMapper.toDTO(l)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) {
        Loan loan = LoanMapper.toEntity(loanDTO);
        Loan nuevoLoan = loanService.createLoan(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(LoanMapper.toDTO(nuevoLoan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDTO) {
        Loan loan = LoanMapper.toEntity(loanDTO);
        Loan loanActualizado = loanService.updateLoan(id, loan);
        return loanActualizado != null ? ResponseEntity.ok(LoanMapper.toDTO(loanActualizado)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        return loanService.deleteLoan(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public Page<Loan> findByFilters(
            @RequestParam(required = false) String nombreJuego,
            @RequestParam(required = false) String nombreCliente,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            Pageable pageable) {
        return loanService.findByFilters(nombreJuego, nombreCliente, fecha, pageable);
    }
}