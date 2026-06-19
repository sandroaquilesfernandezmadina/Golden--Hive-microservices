package org.golden.entity;

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

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String nombres;
    private String apellidos;
    private String telefono;
    private String cargo;

    private LocalDateTime fechaContratacion;
    // facilita el registro de fecha automaticamente.
    @PrePersist
    public void prePersist() {
        this.fechaContratacion = LocalDateTime.now();
    }
    private BigDecimal salario;

}