package edu.unimagdalena.demo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
@Table(name="teachers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
//Una entidad debe tener la notación entity de java persistence entity y algunas otras configuraciones vienen de aca ojo aqui    
//Dependencias las agregamos en el pom.xml
//Si es spring data vamos a pom le decimos add starters y buscamos spring data jpa sql-> guardarlo
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//tipo objetuales no primitivos
    @Column(nullable = false)//restricciones para la primer columna 
    private String firstName;
    @Column(nullable = false)//restricciones para la segunda columna
    private String lastName;
    @Column(unique = true)//restricciones para la tercera columna, codigo unico no se puede repetir
    private String codigo;
    @JsonIgnore//para que no se muestre en el json
    @OneToMany(mappedBy = "teacher")//mappedBy es el nombre de la variable en la otra entidad
    //Se coloca esto por llave foranea que va en cursos 
    private Set<Course> courses;


    public Teacher updateWith(Teacher teacher){
        //Creamos un objeto copia a partir del que le pasamos
        return new Teacher(this.id, teacher.firstName, teacher.lastName, teacher.codigo, null);
    }
    
}
