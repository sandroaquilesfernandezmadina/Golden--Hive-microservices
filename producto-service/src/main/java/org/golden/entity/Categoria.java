package org.golden.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //SIRVE PARA DECLARAR QUE UNA CLASE REPRESENTA A UNA TABLA EN LA BASE DE DATOS
@Getter //Acceso de lectura permite que otras clase pueden consultar
@Setter //permite que otras clases pueden modificar
@AllArgsConstructor // facilita crear constructor para los atributos
@NoArgsConstructor // facilita crear un constructor basio sin argumentos
@Table(name = "categorias") //sirve para especificar el nombre exacto en la bsae de datos
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer categoriaId;
    @Column(name ="nombre_categoria")
    private String nombreCategoria;
}
