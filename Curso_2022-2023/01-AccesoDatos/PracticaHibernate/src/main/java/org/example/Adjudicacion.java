package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Adjudicaciones")
public class Adjudicacion implements Serializable {
    private int id;
    private String fechaAdjudicacion;
    private int valueForTwo;
    private String idProveedor;
    private Set<Proveedor> proveedores;
    private int valueForThree;
    private String recuento;
    private int recuentoOfertas;
    private int valueForOne;
    private String nombreProveedor;
    private String valor;
    private int idLicitacion;
    private Licitacion licitacion;

    public Adjudicacion() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Fecha_Adjudicacion", length = 300)
    public String getFechaAdjudicacion() {
        return fechaAdjudicacion;
    }

    public void setFechaAdjudicacion(String fechaAdjudicacion) {
        this.fechaAdjudicacion = fechaAdjudicacion;
    }

    @Column(name = "Value_For_Two")
    public int getValueForTwo() {
        return valueForTwo;
    }

    public void setValueForTwo(int valueForTwo) {
        this.valueForTwo = valueForTwo;
    }

    @Column(name = "Id_Proveedor", length = 300)
    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    @OneToMany(mappedBy = "adjudicacion")
    public Set<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(Set<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    @Column(name = "Value_For_Three")
    public int getValueForThree() {
        return valueForThree;
    }

    public void setValueForThree(int valueForThree) {
        this.valueForThree = valueForThree;
    }

    @Column(name = "Recuento", length = 300)
    public String getRecuento() {
        return recuento;
    }

    public void setRecuento(String recuento) {
        this.recuento = recuento;
    }

    @Column(name = "Recuento_Ofertas")
    public int getRecuentoOfertas() {
        return recuentoOfertas;
    }

    public void setRecuentoOfertas(int recuentoOfertas) {
        this.recuentoOfertas = recuentoOfertas;
    }

    @Column(name = "Value_For_One")
    public int getValueForOne() {
        return valueForOne;
    }

    public void setValueForOne(int valueForOne) {
        this.valueForOne = valueForOne;
    }

    @Column(name = "Nombre_Proveedor", length = 300)
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    @Column(name = "Valor", length = 300)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Column(name = "Id_Licitacion")
    public int getIdLicitacion() {
        return idLicitacion;
    }

    public void setIdLicitacion(int idLicitacion) {
        this.idLicitacion = idLicitacion;
    }

    @ManyToOne
    @JoinColumn(name = "Id_Licitacion", insertable = false, updatable = false)
    public Licitacion getLicitacion() {
        return licitacion;
    }

    public void setLicitacion(Licitacion licitacion) {
        this.licitacion = licitacion;
    }

    @Override
    public String toString() {
        return "Adjudicacion{" +
                "id=" + id +
                ", fechaAdjudicacion='" + fechaAdjudicacion + '\'' +
                ", valueForTwo=" + valueForTwo +
                ", idProveedor='" + idProveedor + '\'' +
                ", proveedores=" + proveedores +
                ", valueForThree=" + valueForThree +
                ", recuento='" + recuento + '\'' +
                ", recuentoOfertas=" + recuentoOfertas +
                ", valueForOne=" + valueForOne +
                ", nombreProveedor='" + nombreProveedor + '\'' +
                ", valor='" + valor + '\'' +
                ", idLicitacion=" + idLicitacion +
                '}';
    }
}
