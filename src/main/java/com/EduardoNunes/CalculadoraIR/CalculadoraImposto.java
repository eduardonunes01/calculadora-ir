package com.EduardoNunes.CalculadoraIR;

import java.math.BigDecimal;
import java.util.List;

public class CalculadoraImposto {
    public BigDecimal calcularBaseCalculo(DadosContribuinte dados) {
        return dados.getRendimentos().subtract(dados.getDeducoes());
    }

    public BigDecimal calcularImposto(BigDecimal baseCalculo) {

        List<RegraTributaria> tabela = TabelaIR26.obterTabela();
        for (RegraTributaria regra : tabela) {

            boolean acimaDoLimiteInferior = baseCalculo.compareTo(regra.getLimiteInferior()) >= 0;
            boolean abaixoDoLimiteSuperior = regra.getLimiteSuperior() == null
                    || baseCalculo.compareTo(regra.getLimiteSuperior()) <= 0;

            if (acimaDoLimiteInferior && abaixoDoLimiteSuperior) {
                BigDecimal imposto = baseCalculo.multiply(regra.getAliquota()).subtract(regra.getParcelaDeduzir());

                return imposto.max(BigDecimal.ZERO);
            }
        }
        return BigDecimal.ZERO;
    }

    public Resultado calcular(DadosContribuinte dados) {

        BigDecimal baseCalculo = calcularBaseCalculo(dados);
        BigDecimal impostoDevido = calcularImposto(baseCalculo);
        BigDecimal impostoRetido = dados.getImpostoRetido();
        BigDecimal diferenca = impostoDevido.subtract(impostoRetido);

        return new Resultado(
                baseCalculo,
                impostoDevido,
                impostoRetido,
                diferenca
        );
    }
}

