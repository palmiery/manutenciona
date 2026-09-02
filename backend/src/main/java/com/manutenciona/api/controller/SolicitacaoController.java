package com.manutenciona.api.controller;

import com.manutenciona.api.dto.DashboardStatsDTO;
import com.manutenciona.api.dto.SolicitacaoCreateDTO;
import com.manutenciona.api.dto.SolicitacaoDTO;
import com.manutenciona.api.dto.SolicitacaoStatusDTO;
import com.manutenciona.api.enums.StatusSolicitacao;
import com.manutenciona.api.service.SolicitacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoDTO>> findAll(@RequestParam(required = false) StatusSolicitacao status) {
        return ResponseEntity.ok(solicitacaoService.findAll(status));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(solicitacaoService.getDashboardStats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(solicitacaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SolicitacaoDTO> create(@Valid @RequestBody SolicitacaoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.create(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<SolicitacaoDTO> updateStatus(@PathVariable Long id, @Valid @RequestBody SolicitacaoStatusDTO dto) {
        return ResponseEntity.ok(solicitacaoService.updateStatus(id, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> update(@PathVariable Long id, @Valid @RequestBody SolicitacaoCreateDTO dto) {
        return ResponseEntity.ok(solicitacaoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        solicitacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
