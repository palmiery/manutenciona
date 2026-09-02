package com.manutenciona.api.controller;

import com.manutenciona.api.dto.SalaCreateDTO;
import com.manutenciona.api.dto.SalaDTO;
import com.manutenciona.api.service.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @GetMapping
    public ResponseEntity<List<SalaDTO>> findAll() {
        return ResponseEntity.ok(salaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SalaDTO> create(@Valid @RequestBody SalaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaDTO> update(@PathVariable Long id, @Valid @RequestBody SalaCreateDTO dto) {
        return ResponseEntity.ok(salaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        salaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
