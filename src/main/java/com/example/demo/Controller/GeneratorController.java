package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Domain.Formulario;
import com.example.demo.Generator.RelatorioGen;

@Controller
@RequestMapping
public class GeneratorController {

    @GetMapping("/form")
    public ModelAndView getForm() {
        ModelAndView movi = new ModelAndView("telaFormulario.html");
        Formulario formulario = new Formulario();
        movi.addObject("formulario", formulario);
        return movi;
    }

    @PostMapping("/add")
    public String postForm(Formulario formulario) {
        RelatorioGen relatorioGen = new RelatorioGen(formulario.getAluno(), formulario.getTreino(),
                formulario.getExercicio());
        relatorioGen.gerarCabecalho();
        relatorioGen.gerarCorpo();
        relatorioGen.gerarRodape();
        relatorioGen.imprimir();
        return "redirect:/download";
    }

}
