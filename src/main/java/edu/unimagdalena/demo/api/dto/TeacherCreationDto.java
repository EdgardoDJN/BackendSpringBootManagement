package edu.unimagdalena.demo.api.dto;

import lombok.Data;

//POJO -> Atributos encapsulados y su constructor, @Data -> @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
//@Builder
@Data
public class TeacherCreationDto {
    //Para crear necesito el id
    private Long id;
    private String firstName;
    private String lastName;
    private String codigo;
    
}
