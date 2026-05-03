package org.golden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @OneToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;

}
