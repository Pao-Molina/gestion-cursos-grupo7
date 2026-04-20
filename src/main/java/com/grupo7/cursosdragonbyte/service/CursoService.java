package com.grupo7.cursosdragonbyte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo7.cursosdragonbyte.model.entity.Curso;
import com.grupo7.cursosdragonbyte.model.enums.CategoriaCurso;
import com.grupo7.cursosdragonbyte.repository.CursoRepository;

@Service
public class CursoService {

@Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Curso crearCurso(Curso curso) {
        
        return cursoRepository.save(curso);
    }

    @Transactional(readOnly = true)
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Curso> listarPorCategoria(CategoriaCurso categoria) {
        return cursoRepository.findByCategoria(categoria);
    }

    @Transactional(readOnly = true)
    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso con ID " + id + " no encontrado."));
    }

    @Transactional
    public Curso actualizarCurso(Long id, Curso cursoDatos) {
        Curso curso = obtenerPorId(id);
        
        curso.setNombreCurso(cursoDatos.getNombreCurso());
        curso.setDescripcionCurso(cursoDatos.getDescripcionCurso());
        curso.setNumeroNiveles(cursoDatos.getNumeroNiveles());
        curso.setCategoria(cursoDatos.getCategoria());
        curso.setDificultad(cursoDatos.getDificultad());
        
        return cursoRepository.save(curso);
    }
}
