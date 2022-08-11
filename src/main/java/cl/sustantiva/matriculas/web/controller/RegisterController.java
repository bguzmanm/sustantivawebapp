package cl.sustantiva.matriculas.web.controller;

import cl.sustantiva.matriculas.model.domain.dto.Grade;
import cl.sustantiva.matriculas.model.domain.dto.Register;
import cl.sustantiva.matriculas.model.domain.dto.Student;
import cl.sustantiva.matriculas.model.domain.service.GradeService;
import cl.sustantiva.matriculas.model.domain.service.RegisterService;
import cl.sustantiva.matriculas.model.domain.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/register")
public class RegisterController {

    Log logger = LogFactory.getLog(RegisterController.class);
    private final RegisterService service;
    private final StudentService studentService;
    private final GradeService gradeService;

    public RegisterController(RegisterService service, StudentService studentService, GradeService gradeService) {
        this.service = service;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }


    @GetMapping("/all")
    public String findAll(Model model){
        model.addAttribute("registers",
                service.findAll()
                        .orElse(new ArrayList<Register>()));
        return "registerList";
    }

    @GetMapping("/{studentId}/{gradeId}")
    public String editById(@PathVariable("studentId") int studentId,
                           @PathVariable("gradeId") int gradeId,
                           Model model){

        model.addAttribute("register",
                service.findById(studentId, gradeId)
                        .orElse(new Register()));
        return "registerEdit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Register register){
        logger.info(register.toString());

        service.save(register);
        return "redirect:/register/all";
    }

    @GetMapping("/new")
    public String add(Model model){

        model.addAttribute("students",
                studentService.findAll()
                        .orElse(new ArrayList<Student>()));
        model.addAttribute("grades",
                gradeService.findAll()
                        .orElse(new ArrayList<Grade>()));

        return "registerNew";
    }

    @GetMapping("/del/{studentId}/{gradeId}")
    public String delete(@PathVariable("studentId") int studentId,
                         @PathVariable("gradeId") int gradeId){
        service.delete(studentId, gradeId);
        return "redirect:/register/all";
    }

}
