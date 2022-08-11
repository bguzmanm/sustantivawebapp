package cl.sustantiva.matriculas.model.domain.service;

import cl.sustantiva.matriculas.model.domain.dto.Grade;
import cl.sustantiva.matriculas.model.domain.dto.Register;
import cl.sustantiva.matriculas.model.domain.dto.Student;
import cl.sustantiva.matriculas.model.domain.repository.RegisterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    private final RegisterRepository repository;
    private final StudentService studentService;
    private final GradeService gradeService;

    public RegisterService(RegisterRepository repository, StudentService studentService, GradeService gradeService) {
        this.repository = repository;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }
    public Optional<List<Register>> findAll(){
        return repository.findAll();
    }

    public Optional<Register> findById(int studentId, int gradeId){
        return repository.findById(studentId, gradeId);
    }
    public Register save(Register register){

        if (repository.existsById(register.getStudentId(), register.getGradeId())){
            //haremos un update
        } else {
            //haremos un insert
        }

        if (register.getGrade() == null){
            register.setGrade(
                    gradeService.findById(register.getGradeId())
                            .orElse(new Grade()));
        }

        if (register.getStudent() == null){
            register.setStudent(
                    studentService.findById(register.getStudentId())
                            .orElse(new Student()));
        }

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
