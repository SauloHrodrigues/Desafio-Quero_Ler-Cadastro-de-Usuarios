package com.queroler.CadastroDeUsuario.controllers;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoUpdateDto;
import com.queroler.CadastroDeUsuario.service.DocumentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documentos")
public class DocumentoContoller {

    private final DocumentoService service;

    @PostMapping
    ResponseEntity<DocumentoResponseDto> criar(@RequestBody @Valid DocumentoRequestDto dto){
        DocumentoResponseDto response = service.criar(dto);
        URI location = URI.create("/documentos/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    ResponseEntity<Page<DocumentoResponseDto>> listar(Pageable pageable){
        Page<DocumentoResponseDto> page = service.listar(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid DocumentoUpdateDto dto) {
        service.atualizar(id, dto);
        return ResponseEntity.noContent().build();
    }
}