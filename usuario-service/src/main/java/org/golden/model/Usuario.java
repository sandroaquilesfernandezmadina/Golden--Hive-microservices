package org.golden.model;

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
    private String suerName;
    private String correo;
    private String passwordHash;
    private EstadoUsuario estado;
    private LocalDateTime fechaRegistro;

    //facilita el registro de fecha automatico
    @PrePersist
    public void prePersist(){
        this.fechaRegistro = LocalDateTime.now();
    }
}
