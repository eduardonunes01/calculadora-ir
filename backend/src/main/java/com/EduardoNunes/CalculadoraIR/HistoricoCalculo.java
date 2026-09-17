import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_calculos")
public class HistoricoCalculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal rendimentos;
    private BigDecimal deducoes;
    private BigDecimal impostoRetido;
    private BigDecimal baseCalculo;
    private BigDecimal impostoDevido;
    private BigDecimal diferenca;

    private LocalDateTime dataHora;

    @PrePersist
    public void preenchDataHora() {
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRendimentos() {
        return rendimentos;
    }

    public void setRendimentos(BigDecimal rendimentos) {
        this.rendimentos = rendimentos;
    }

    public BigDecimal getDeducoes() {
        return deducoes;
    }

    public void setDeducoes(BigDecimal deducoes) {
        this.deducoes = deducoes;
    }

    public BigDecimal getImpostoRetido() {
        return impostoRetido;
    }

    public void setImpostoRetido(BigDecimal impostoRetido) {
        this.impostoRetido = impostoRetido;
    }

    public BigDecimal getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(BigDecimal baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public BigDecimal getImpostoDevido() {
        return impostoDevido;
    }

    public void setImpostoDevido(BigDecimal impostoDevido) {
        this.impostoDevido = impostoDevido;
    }

    public BigDecimal getDiferenca() {
        return diferenca;
    }

    public void setDiferenca(BigDecimal diferenca) {
        this.diferenca = diferenca;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}