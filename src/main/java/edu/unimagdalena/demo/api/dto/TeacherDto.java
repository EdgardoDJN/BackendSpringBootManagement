package edu.unimagdalena.demo.api.dto;

import lombok.Data;

@Data
public class TeacherDto {
    //Puedo tener uno o mas Dto para teacher 
    //Dependiendo de lo que necesite
    //O se puede aplicar un JsonProperty para que los valores que no los tenga no los muestre
    //Podemos quitar el JsonIgnore de la entidad, porque vamos a manejar los datos mejores
    //El json no lo va a manejar a partir de un entity sino nosotros sobre Dto
    //y No el mappeador de json de spring 
    private String firstName;
    private String lastName;
    private String codigo;
}
