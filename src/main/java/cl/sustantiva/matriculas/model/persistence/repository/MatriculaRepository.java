package cl.sustantiva.matriculas.model.persistence.repository;

import cl.sustantiva.matriculas.model.domain.dto.Register;
import cl.sustantiva.matriculas.model.domain.repository.RegisterRepository;
import cl.sustantiva.matriculas.model.persistence.crud.MatriculaCrud;
import cl.sustantiva.matriculas.model.persistence.entity.Matricula;
import cl.sustantiva.matriculas.model.persistence.entity.MatriculaPK;
import cl.sustantiva.matriculas.model.persistence.mapper.RegisterMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MatriculaRepository implements RegisterRepository {

    private final MatriculaCrud crud;
    private final RegisterMapper mapper;

    public MatriculaRepository(MatriculaCrud crud, RegisterMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<Register>> findAll() {
        return Optional.of(mapper.toRegisters((List<Matricula>) crud.findAll()));
    }

    @Override
    public Optional<Register> findById(int studentId, int gradeId) {
        return crud.findById(new MatriculaPK(studentId, gradeId))
                .map(mapper::toRegister);
    }

    @Override
    public Register save(Register register) {
        return mapper.toRegister(crud.save(mapper.toMatricula(register)));
    }

    @Override
    public void delete(int studentId, int gradeId) {
        crud.deleteById(new MatriculaPK(studentId, gradeId));
    }

    @Override
    public boolean existsById(int studentId, int gradeId) {
        return crud.existsById(new MatriculaPK(studentId, gradeId));
    }

}
