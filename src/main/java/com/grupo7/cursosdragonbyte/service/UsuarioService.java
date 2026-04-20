package com.grupo7.cursosdragonbyte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo7.cursosdragonbyte.model.entity.Usuario;
import com.grupo7.cursosdragonbyte.repository.UsuarioRepository;

@Service
public class UsuarioService {

@Autowired
private UsuarioRepository usuarioRepository;


@Transactional
public Usuario registrarUsuario(Usuario usuario) {
    if (usuarioRepository.existsByEmail(usuario.getEmail())) {
        throw new RuntimeException("Error: El email " + usuario.getEmail() + " ya está en uso.");
        }
        return usuarioRepository.save(usuario);
    }

@Transactional(readOnly = true)
public List<Usuario> obtenerTodos() {
    return usuarioRepository.findAll();
    }

@Transactional(readOnly = true)
public Usuario obtenerPorId(Long id) {
    return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado."));
    }

@Transactional
public void eliminarUsuario(Long id) {
    if (!usuarioRepository.existsById(id)) {
        throw new RuntimeException("No se puede eliminar: Usuario no encontrado.");
        }
        usuarioRepository.deleteById(id);
    }
}
