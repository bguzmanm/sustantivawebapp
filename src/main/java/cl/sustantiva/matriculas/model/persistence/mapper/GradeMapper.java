package cl.sustantiva.matriculas.model.persistence.mapper;

import cl.sustantiva.matriculas.model.domain.dto.Grade;
import cl.sustantiva.matriculas.model.persistence.entity.Curso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface GradeMapper {

    @Mappings({
            @Mapping(source = "id", target = "gradeId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "codigo", target = "code"),
            @Mapping(source = "facilitadorId", target = "teacherId"),
            @Mapping(source = "facilitador", target = "teacher"),
    })
    Grade toGrade(Curso curso);
    List<Grade> toGrades(List<Curso> cursos);

    @InheritInverseConfiguration
    Curso toCurso(Grade grade);

}
