package org.golden.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    private String userName;

    private String correo;

    private String passwordHash;

    //lo declaramos string por que guardara un "activo" y no un 0
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoUsuario estado;

    private LocalDateTime fechaRegistro;

    //facilita el registro de fecha automatico
    @PrePersist
    public void prePersist(){
        this.fechaRegistro = LocalDateTime.now();
    }
}
