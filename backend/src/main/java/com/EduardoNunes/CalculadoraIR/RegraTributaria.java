package com.EduardoNunes.CalculadoraIR;

import java.math.BigDecimal;

public class RegraTributaria {

    private BigDecimal limiteInferior;
    private BigDecimal limiteSuperior;
    private BigDecimal aliquota;
    private BigDecimal parcelaDeduzir;

    public RegraTributaria(
            BigDecimal limiteInferior,
            BigDecimal limiteSuperior,
            BigDecimal aliquota,
            BigDecimal parcelaDeduzir) {

        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.aliquota = aliquota;
        this.parcelaDeduzir = parcelaDeduzir;
    }

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public BigDecimal getLimiteSuperior() {
        return limiteSuperior;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public BigDecimal getParcelaDeduzir() {
        return parcelaDeduzir;
    }
}

