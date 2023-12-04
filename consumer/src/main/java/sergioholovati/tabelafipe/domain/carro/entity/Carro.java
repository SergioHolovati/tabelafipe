package sergioholovati.tabelafipe.domain.carro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

import static jakarta.persistence.GenerationType.IDENTITY;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @JoinColumn(name= "marcaId", nullable = false)
    @ManyToOne
    private Marca marca;

    @Column
    private String observacao;
}
