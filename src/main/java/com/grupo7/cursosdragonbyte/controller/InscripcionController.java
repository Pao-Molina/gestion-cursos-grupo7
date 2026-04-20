package com.grupo7.cursosdragonbyte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.cursosdragonbyte.model.entity.Inscripcion;
import com.grupo7.cursosdragonbyte.service.InscripcionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/inscripciones")
@Tag(name = "Inscripciones", description = "Endpoints para gestionar la inscripción de usuarios en cursos")
public class InscripcionController {

@Autowired
    private InscripcionService inscripcionService;

    @PostMapping("/usuario/{usuarioId}/curso/{cursoId}")
    @Operation(summary = "Inscribir usuario", description = "Crea una nueva inscripción vinculando un usuario y un curso mediante sus IDs.")
    public ResponseEntity<Inscripcion> inscribir(
            @PathVariable Long usuarioId, 
            @PathVariable Long cursoId) {
        try {
            Inscripcion nuevaInscripcion = inscripcionService.inscribirUsuario(usuarioId, cursoId);
            return ResponseEntity.ok(nuevaInscripcion);
        } catch (RuntimeException e) {
            // Si falla la validación (ej: ya está inscrito), devuelve un error 400
            return ResponseEntity.badRequest().header("Error-Message", e.getMessage()).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar cursos por usuario", description = "Obtiene todas las inscripciones activas o completadas de un estudiante específico.")
    public List<Inscripcion> listarPorUsuario(@PathVariable Long usuarioId) {
        return inscripcionService.obtenerCursosDeUsuario(usuarioId);
    }

    @PatchMapping("/{id}/completar")
    @Operation(summary = "Completar curso", description = "Cambia el estado de una inscripción a 'COMPLETADO' para permitir el avance en la plataforma.")
    public ResponseEntity<Inscripcion> finalizarCurso(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(inscripcionService.completarCurso(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
