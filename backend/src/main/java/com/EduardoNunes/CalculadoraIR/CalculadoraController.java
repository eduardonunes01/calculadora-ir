package com.EduardoNunes.CalculadoraIR;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/calcular")
public class CalculadoraController {

    private final CalculadoraImposto calculadora = new CalculadoraImposto();
    private final HistoricoCalculoRepository repository;

    public CalculadoraController(HistoricoCalculoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Resultado calcular(@RequestBody DadosContribuinte dados) {
        Resultado resultado = calculadora.calcular(dados);

        HistoricoCalculo historico = new HistoricoCalculo();
        historico.setRendimentos(dados.getRendimentos());
        historico.setDeducoes(dados.getDeducoes());
        historico.setImpostoRetido(dados.getImpostoRetido());
        historico.setBaseCalculo(resultado.getBaseCalculo());
        historico.setImpostoDevido(resultado.getImpostoDevido());
        historico.setDiferenca(resultado.getDiferenca());
        repository.save(historico);

        return resultado;
    }

    @GetMapping("/historico")
    public List<HistoricoCalculo> historico() {
        return repository.findAll();
    }
}