package org.example;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Proveedores")
public class Proveedor implements Serializable {
    private int id;
    private String nombreProveedor;
    private String idProveedor;
    private String slugProveedor;
    private int idAdjudicacion;
    private Adjudicacion adjudicacion;

    public Proveedor() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Nombre_Proveedor", length = 300)
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    @Column(name = "Id_Proveedor", length = 300)
    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Column(name = "Slug_Proveedor", length = 300)
    public String getSlugProveedor() {
        return slugProveedor;
    }

    public void setSlugProveedor(String slugProveedor) {
        this.slugProveedor = slugProveedor;
    }

    @Column(name = "Id_Adjudicacion")
    public int getIdAdjudicacion() {
        return idAdjudicacion;
    }

    public void setIdAdjudicacion(int idAdjudicacion) {
        this.idAdjudicacion = idAdjudicacion;
    }

    @ManyToOne()
    @JoinColumn(name = "Id_Adjudicacion", insertable = false, updatable = false)
    public Adjudicacion getAdjudicacion() {
        return adjudicacion;
    }

    public void setAdjudicacion(Adjudicacion adjudicacion) {
        this.adjudicacion = adjudicacion;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", nombreProveedor='" + nombreProveedor + '\'' +
                ", idProveedor='" + idProveedor + '\'' +
                ", slugProveedor='" + slugProveedor + '\'' +
                ", idAdjudicacion=" + idAdjudicacion +
                '}';
    }
}
