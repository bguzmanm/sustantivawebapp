package cl.sustantiva.matriculas.model.domain.service;

import cl.sustantiva.matriculas.model.domain.dto.Register;
import cl.sustantiva.matriculas.model.domain.repository.RegisterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    private final RegisterRepository repository;

    public RegisterService(RegisterRepository repository) {
        this.repository = repository;
    }

    public Optional<List<Register>> findAll(){
        return repository.findAll();
    }

    public Optional<Register> findById(int studentId, int gradeId){
        return repository.findById(studentId, gradeId);
    }
    public Register save(Register register){
        return repository.save(register);
    }

    public boolean delete(int studentId, int gradeId){
        return findById(studentId, gradeId)
                .map(register -> {
                    repository.delete(studentId, gradeId);
                    return true;
                })
                .orElse(false);
    }
}
