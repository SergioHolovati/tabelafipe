package sergioholovati.tabelafipe.domain.carro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "carro")
public class Carro  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
