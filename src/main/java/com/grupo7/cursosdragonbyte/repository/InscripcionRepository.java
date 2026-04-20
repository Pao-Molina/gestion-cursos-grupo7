package com.grupo7.cursosdragonbyte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo7.cursosdragonbyte.model.entity.Inscripcion;
import com.grupo7.cursosdragonbyte.model.enums.EstadoInscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

List<Inscripcion> findByUsuarioId(Long usuarioId);
    
List<Inscripcion> findByEstado(EstadoInscripcion estado);
    
boolean existsByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);

}
