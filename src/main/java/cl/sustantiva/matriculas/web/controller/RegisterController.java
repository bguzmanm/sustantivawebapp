package cl.sustantiva.matriculas.web.controller;

import cl.sustantiva.matriculas.model.domain.dto.Register;
import cl.sustantiva.matriculas.model.domain.service.RegisterService;
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

    public RegisterController(RegisterService service) {
        this.service = service;
    }
    @GetMapping("/all")
    public String findAll(Model model){
        logger.info(service.findAll().isPresent());
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
        service.save(register);
        return "redirect:/register/all";
    }

    @GetMapping("/del/{studentId}/{gradeId}")
    public String delete(@PathVariable("studentId") int studentId,
                         @PathVariable("gradeId") int gradeId){
        service.delete(studentId, gradeId);
        return "redirect:/register/all";
    }

}
