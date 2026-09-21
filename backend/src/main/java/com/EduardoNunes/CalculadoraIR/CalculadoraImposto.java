package com.EduardoNunes.CalculadoraIR;

import java.math.BigDecimal;
import java.util.List;

public class CalculadoraImposto {

    public BigDecimal calcularBaseCalculo(DadosContribuinte dados) {
        return dados.getRendimentos().subtract(dados.getDeducoes());
    }

    public RegraTributaria encontrarFaixa(BigDecimal baseCalculo) {
        List<RegraTributaria> tabela = TabelaIR26.obterTabela();
        for (RegraTributaria regra : tabela) {
            boolean acimaDoLimiteInferior = baseCalculo.compareTo(regra.getLimiteInferior()) >= 0;
            boolean abaixoDoLimiteSuperior = regra.getLimiteSuperior() == null
                    || baseCalculo.compareTo(regra.getLimiteSuperior()) <= 0;

            if (acimaDoLimiteInferior && abaixoDoLimiteSuperior) {
                return regra;
            }
        }
        return null;
    }

    public BigDecimal calcularImposto(BigDecimal baseCalculo, RegraTributaria regra) {
        if (regra == null) return BigDecimal.ZERO;
        BigDecimal imposto = baseCalculo.multiply(regra.getAliquota()).subtract(regra.getParcelaDeduzir());
        return imposto.max(BigDecimal.ZERO);
    }

    public Resultado calcular(DadosContribuinte dados) {
        BigDecimal baseCalculo = calcularBaseCalculo(dados);
        RegraTributaria regra = encontrarFaixa(baseCalculo);
        BigDecimal impostoDevido = calcularImposto(baseCalculo, regra);
        BigDecimal impostoRetido = dados.getImpostoRetido();
        BigDecimal diferenca = impostoDevido.subtract(impostoRetido);

        FaixaTributariaDTO faixaAplicada = regra == null ? null : new FaixaTributariaDTO(
                regra.getLimiteInferior(),
                regra.getLimiteSuperior(),
                regra.getAliquota(),
                regra.getParcelaDeduzir()
        );

        return new Resultado(
                baseCalculo,
                impostoDevido,
                impostoRetido,
                diferenca,
                faixaAplicada
        );
    }
}