package com.grupo7.cursosdragonbyte.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grupo7.cursosdragonbyte.model.embeddable.UbicacionUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")

public class Usuario extends BaseEntity {

@Column(length = 50, nullable = false)
private String nombre;

@Column(length = 50, nullable = false)
private String apellido;

@Column(length = 20, nullable = true)
private String genero;

@Column(length = 20)            
private Integer edad;

@Column(unique = true, nullable = false, length = 100)
private String email;

@Column(length = 100, nullable = false)
private String password;

@Embedded
private UbicacionUsuario ubicacion;

@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonManagedReference 
private List<Inscripcion> inscripciones = new ArrayList<>();

}
