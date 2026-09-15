package com.EduardoNunes.CalculadoraIR;

import java.math.BigDecimal;
import java.util.List;

public class TabelaIR26 {

    public static List<RegraTributaria> obterTabela() {
        return List.of(
                new RegraTributaria(
                        new BigDecimal("0.00"),
                        new BigDecimal("29145.60"),
                        new BigDecimal("0.00"),
                        new BigDecimal("0.00")
                ),
                new RegraTributaria(
                        new BigDecimal("29145.61"),
                        new BigDecimal("33919.80"),
                        new BigDecimal("0.075"),
                        new BigDecimal("2185.92")
                ),
                new RegraTributaria(
                        new BigDecimal("33919.81"),
                        new BigDecimal("45012.60"),
                        new BigDecimal("0.15"),
                        new BigDecimal("4729.91")
                ),
                new RegraTributaria(
                        new BigDecimal("45012.61"),
                        new BigDecimal("55976.16"),
                        new BigDecimal("0.225"),
                        new BigDecimal("8105.85")
                ),
                new RegraTributaria(
                        new BigDecimal("55976.17"),
                        null,
                        new BigDecimal("0.275"),
                        new BigDecimal("10904.66")
                )
        );
    }
}