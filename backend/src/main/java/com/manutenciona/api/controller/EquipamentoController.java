package com.manutenciona.api.controller;

import com.manutenciona.api.dto.EquipamentoCreateDTO;
import com.manutenciona.api.dto.EquipamentoDTO;
import com.manutenciona.api.service.EquipamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @GetMapping
    public ResponseEntity<List<EquipamentoDTO>> findAll(@RequestParam(required = false) Long salaId) {
        return ResponseEntity.ok(equipamentoService.findAll(salaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(equipamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EquipamentoDTO> create(@Valid @RequestBody EquipamentoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> update(@PathVariable Long id, @Valid @RequestBody EquipamentoCreateDTO dto) {
        return ResponseEntity.ok(equipamentoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
