# Calculadora de IRPF 2026

Aplicação em Java que calcula o Imposto de Renda Pessoa Física (IRPF) anual a partir dos rendimentos, deduções e imposto já retido na fonte, informando se o contribuinte tem **imposto a pagar** ou **a restituir**.

Projeto de estudo desenvolvido para praticar orientação a objetos, uso correto de `BigDecimal` para valores monetários e organização de código em camadas.

## Como funciona

O cálculo segue três etapas:

1. **Base de cálculo** = rendimentos − deduções
2. **Imposto devido** = base de cálculo × alíquota − parcela a deduzir (conforme a faixa da tabela progressiva)
3. **Diferença** = imposto devido − imposto retido
   - Positiva: imposto a pagar
   - Negativa: imposto a restituir

### Tabela progressiva anual utilizada

| Base de cálculo (R$)        | Alíquota | Parcela a deduzir (R$) |
|-----------------------------|----------|------------------------|
| Até 29.145,60               | Isento   | 0,00                   |
| 29.145,61 a 33.919,80       | 7,5%     | 2.185,92               |
| 33.919,81 a 45.012,60       | 15%      | 4.729,91               |
| 45.012,61 a 55.976,16       | 22,5%    | 8.105,85               |
| Acima de 55.976,16          | 27,5%    | 10.904,66              |

> Os valores da tabela ficam isolados na classe `TabelaIR26`, então é só alterar pra atualizar as faixas em anos futuros.

## Estrutura do projeto

```
.
├── Main.java                 # Ponto de entrada: monta um exemplo e imprime o resultado
├── DadosContribuinte.java    # Dados de entrada (rendimentos, deduções, imposto retido)
├── CalculadoraImposto.java   # Regras de cálculo (base, imposto devido, diferença)
├── RegraTributaria.java      # Uma faixa da tabela (limites, alíquota, parcela a deduzir)
├── TabelaIR26.java           # Tabela progressiva do IRPF 2026
└── Resultado.java            # Resultado final do cálculo
```

| Classe | Responsabilidade |
|--------|------------------|
| `DadosContribuinte` | Guarda os dados informados pelo contribuinte |
| `RegraTributaria` | Representa uma faixa da tabela progressiva |
| `TabelaIR26` | Fornece a lista de faixas (`obterTabela()`) |
| `CalculadoraImposto` | Encontra a faixa correta e aplica a fórmula |
| `Resultado` | Transporta base, imposto devido, imposto retido e diferença |

## Tecnologias

- Java 17+ (usa `List.of`)
- `java.math.BigDecimal` pra precisão em cálculos monetários

## Como executar

Pré-requisito: JDK 17 ou superior instalado.

```bash
# compilar
javac *.java

# executar
java Main
```

Ou abra a pasta no IntelliJ IDEA e execute a classe `Main`.

## Exemplo

Entrada definida em `Main.java`:

| Campo | Valor |
|-------|-------|
| Rendimentos | R$ 72.000,00 |
| Deduções | R$ 12.000,00 |
| Imposto retido | R$ 5.000,00 |

Cálculo:

- Base de cálculo: 72.000,00 − 12.000,00 = **60.000,00**
- Faixa de 27,5%: 60.000,00 × 0,275 − 10.904,66 = **5.595,34**
- Diferença: 5.595,34 − 5.000,00 = **595,34 a pagar**

Saída:

```
Rendimentos: R$72000.00
Deduções: R$12000.00
Base de cálculo: R$60000.00
Imposto devido: R$5595.34
Imposto retido: R$5000.00
Diferença: R$595.34
```

## Aviso

Projeto com fins educacionais. Os resultados não substituem o programa oficial da Receita Federal nem a orientação de um contador.

## Autor

**Eduardo Nunes**
[LinkedIn](https://linkedin.com/in/eduardonunes-) · [GitHub](https://github.com/eduardonunes01)
