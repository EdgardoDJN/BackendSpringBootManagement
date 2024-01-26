package edu.unimagdalena.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@data tiene solo constructor por parametros
@Entity
@Table(name = "course_material")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterial {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//podemos utilizar Integer(pequeño), Long(medio), BitDecimal(grande).
    
    private String url;

    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;


}
