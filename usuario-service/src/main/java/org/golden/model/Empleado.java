package org.golden.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empleadoId;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    private String nombres;
    private String apellidos;
    private String telefono;
    private String cargo;

    private LocalDateTime fechaContratacion;
    private BigDecimal salario;

}
