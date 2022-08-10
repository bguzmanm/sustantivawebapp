package cl.sustantiva.matriculas.model.persistence.mapper;

import cl.sustantiva.matriculas.model.domain.dto.Register;
import cl.sustantiva.matriculas.model.persistence.entity.Matricula;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, GradeMapper.class})
public interface RegisterMapper {
    
    @Mappings({
            @Mapping(source = "id.cursoId", target = "gradeId"),
            @Mapping(source = "id.estudianteId", target = "studentId"),
            @Mapping(source = "curso", target = "grade"),
            @Mapping(source = "estudiante", target = "student"),
            @Mapping(source = "fecha", target = "date"),
    })
    Register toRegister(Matricula matricula);
    List<Register> toRegisters(List<Matricula> matricula);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "gradeId", target = "id.cursoId"),
            @Mapping(source = "studentId", target = "id.estudianteId")
    })
    Matricula toMatricula(Register register);

}
