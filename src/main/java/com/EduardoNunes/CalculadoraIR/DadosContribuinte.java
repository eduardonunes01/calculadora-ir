package com.EduardoNunes.CalculadoraIR;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class DadosContribuinte {

    private BigDecimal rendimentos;
    private BigDecimal deducoes;
    private BigDecimal ImpostoRetido;

    @JsonCreator
    public DadosContribuinte(
            @JsonProperty("rendimentos") BigDecimal rendimentos,
            @JsonProperty("deducoes") BigDecimal deducoes,
            @JsonProperty("impostoRetido") BigDecimal ImpostoRetido) {

        if (rendimentos == null || deducoes == null || ImpostoRetido == null) {
            throw new IllegalArgumentException("Rendimentos, deduções e imposto retido não podem ser nulos.");
        }
        if (rendimentos.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Rendimentos não podem ser negativos.");
        }
        if (deducoes.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deduções não podem ser negativas.");
        }
        if (ImpostoRetido.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Imposto retido não pode ser negativo.");
        }

        this.rendimentos = rendimentos;
        this.deducoes = deducoes;
        this.ImpostoRetido = ImpostoRetido;
    }

    public BigDecimal getRendimentos() {
        return rendimentos;
    }

    public BigDecimal getDeducoes() {
        return deducoes;
    }

    public BigDecimal getImpostoRetido() {
        return ImpostoRetido;
    }
}