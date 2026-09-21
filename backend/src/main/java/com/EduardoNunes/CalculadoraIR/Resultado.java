package com.EduardoNunes.CalculadoraIR;

import java.math.BigDecimal;

public class Resultado {

    private BigDecimal baseCalculo;
    private BigDecimal impostoDevido;
    private BigDecimal impostoRetido;
    private BigDecimal diferenca;
    private FaixaTributariaDTO faixaAplicada;

    public Resultado(
            BigDecimal baseCalculo,
            BigDecimal impostoDevido,
            BigDecimal impostoRetido,
            BigDecimal diferenca,
            FaixaTributariaDTO faixaTributariaDTO) {

        this.baseCalculo = baseCalculo;
        this.impostoDevido = impostoDevido;
        this.impostoRetido = impostoRetido;
        this.diferenca = diferenca;
        this.faixaAplicada = faixaAplicada;
    }

    public BigDecimal getBaseCalculo() {
        return baseCalculo;
    }

    public BigDecimal getImpostoDevido() {
        return impostoDevido;
    }

    public BigDecimal getImpostoRetido() {
        return impostoRetido;
    }

    public BigDecimal getDiferenca() {
        return diferenca;
    }

    public FaixaTributariaDTO getFaixaAplicada() {
        return faixaAplicada;
    }
}
