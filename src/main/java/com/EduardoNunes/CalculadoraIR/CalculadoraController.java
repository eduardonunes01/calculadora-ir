package com.EduardoNunes.CalculadoraIR;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calcular")
public class CalculadoraController {

    private final CalculadoraImposto calculadora = new CalculadoraImposto();

    @PostMapping
    public Resultado calcular(@RequestBody DadosContribuinte dados) {
        return calculadora.calcular(dados);
    }
}