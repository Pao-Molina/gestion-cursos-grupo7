package com.grupo7.cursosdragonbyte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.cursosdragonbyte.model.entity.Usuario;
import com.grupo7.cursosdragonbyte.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

@Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Registrar usuario", description = "Crea un nuevo estudiante en la plataforma. El email debe ser único.")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(usuarioService.registrarUsuario(usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().header("Error-Message", e.getMessage()).build();
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos", description = "Obtiene la lista completa de usuarios registrados.")
    public List<Usuario> listarTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener perfil", description = "Recupera la información detallada de un usuario, incluyendo su ubicación e inscripciones.")
    public ResponseEntity<Usuario> obtenerPerfil(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cuenta", description = "Elimina un usuario y sus registros asociados (inscripciones) por su ID.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
