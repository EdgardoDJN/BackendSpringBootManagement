package edu.unimagdalena.demo.entidades;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//incluye getter, setter y el argumento de todos los parametros
@Entity
@Table(name = "students")
@NoArgsConstructor//constructor por defecto
@AllArgsConstructor//constructor con todos los parametros
public class Student {
    @Id//va todo seguido
    @GeneratedValue(strategy = GenerationType.IDENTITY) //va todo seguido
    private Long id;//va todo seguido
    @Column(nullable = false)
    private String lastName;//si yo no le coloco @Column ya sobreentiende que es el mismo nombre de la variable
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false, unique = true)
    private String codigo;
    @Column
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)//nos dice si es MALE o FEMALE y ordinal es el numero dependiendo de la posicion
    private Gender gender;
    
    @JsonIgnore//para que no se muestre en el json
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}
