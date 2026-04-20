package com.grupo7.cursosdragonbyte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.cursosdragonbyte.model.entity.Curso;
import com.grupo7.cursosdragonbyte.model.enums.CategoriaCurso;
import com.grupo7.cursosdragonbyte.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

@Autowired
    private CursoService cursoService;

    @PostMapping
    @Operation(summary = "Crear curso", description = "Registra un nuevo curso en la base de datos.")
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.crearCurso(curso));
    }

    @GetMapping
    @Operation(summary = "Listar todos", description = "Retorna una lista con todos los cursos disponibles.")
    public List<Curso> listar() {
        return cursoService.listarCursos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por ID", description = "Busca un curso específico según su identificador único.")
    public ResponseEntity<Curso> obtener(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cursoService.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Listar por categoría", description = "Filtra los cursos por categorías como PROGRAMACION, INGLES o HABITOS.")
    public List<Curso> listarPorCategoria(@PathVariable CategoriaCurso categoria) {
        return cursoService.listarPorCategoria(categoria);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar curso", description = "Modifica los datos de un curso existente.")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody Curso cursoDatos) {
        try {
            return ResponseEntity.ok(cursoService.actualizarCurso(id, cursoDatos));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
