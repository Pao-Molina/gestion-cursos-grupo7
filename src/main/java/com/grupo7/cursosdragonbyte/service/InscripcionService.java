package com.grupo7.cursosdragonbyte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo7.cursosdragonbyte.model.entity.Curso;
import com.grupo7.cursosdragonbyte.model.entity.Inscripcion;
import com.grupo7.cursosdragonbyte.model.entity.Usuario;
import com.grupo7.cursosdragonbyte.model.enums.EstadoInscripcion;
import com.grupo7.cursosdragonbyte.repository.CursoRepository;
import com.grupo7.cursosdragonbyte.repository.InscripcionRepository;
import com.grupo7.cursosdragonbyte.repository.UsuarioRepository;

@Service
public class InscripcionService {

@Autowired
private InscripcionRepository inscripcionRepository;

@Autowired
private UsuarioRepository usuarioRepository;

@Autowired
private CursoRepository cursoRepository;

    
@Transactional
public Inscripcion inscribirUsuario(Long usuarioId, Long cursoId) {
        
        
    Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("El usuario con ID " + usuarioId + " no existe."));

        
    Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("El curso con ID " + cursoId + " no existe."));

        
    if (inscripcionRepository.existsByUsuarioIdAndCursoId(usuarioId, cursoId)) {
        throw new RuntimeException("El usuario ya se encuentra inscrito en este curso.");
        }
  
    Inscripcion nuevaInscripcion = new Inscripcion();
    nuevaInscripcion.setUsuario(usuario);
    nuevaInscripcion.setCurso(curso);
    nuevaInscripcion.setEstado(EstadoInscripcion.COMPLETO);
  
    return inscripcionRepository.save(nuevaInscripcion);
    }

    

@Transactional(readOnly = true)
public List<Inscripcion> obtenerCursosDeUsuario(Long usuarioId) {
    return inscripcionRepository.findByUsuarioId(usuarioId);
    }

    
@Transactional
public Inscripcion completarCurso(Long inscripcionId) {
    Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId)
            .orElseThrow(() -> new RuntimeException("Inscripción no encontrada."));

    inscripcion.setEstado(EstadoInscripcion.COMPLETO);
        
        

    return inscripcionRepository.save(inscripcion);
    }
}
