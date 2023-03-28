package edu.unimagdalena.demo.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")//asi se llama la foreing key en la tabla
    private Teacher teacher;//muchos cursos tienen un solo profesor por eso utilizamos un objeto de tipo teacher
    //va de acuero a lo que este en Teacher como es teacher por eso lleva ese nombre


    @ManyToMany()
    @JoinTable(name="COURSES_STUDENTS", joinColumns = @JoinColumn(name="course_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="student_id", referencedColumnName = "id"))
    private Set<Student> students = new HashSet<>();
    //un curso puede tener muchos estudiantes y un estudiante puede tener muchos cursos
    //por eso es una lista de estudiantes

    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;


    
}
