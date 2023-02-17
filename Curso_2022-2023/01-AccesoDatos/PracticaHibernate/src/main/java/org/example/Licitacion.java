package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Licitaciones")
public class Licitacion implements Serializable {
    private int id;
    private String idApi;
    private String fecha;
    private String fechaLimite;
    private String diasFechaLimite;
    private String titulo;
    private String category;
    private String sid;
    private String url;
    private Set<Adjudicacion> adjudicaciones;

    public Licitacion() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Id_API", length = 300)
    public String getIdApi() {
        return idApi;
    }

    public void setIdApi(String idApi) {
        this.idApi = idApi;
    }

    @Column(name = "Fecha", length = 300)
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Column(name = "Fecha_Limite", length = 300)
    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Column(name = "Dias_Hasta_Fecha_Limite", length = 300)
    public String getDiasFechaLimite() {
        return diasFechaLimite;
    }

    public void setDiasFechaLimite(String diasFechaLimite) {
        this.diasFechaLimite = diasFechaLimite;
    }

    @Column(name = "Titulo", length = 8000)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = "Categoria", length = 300)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "SID", length = 300)
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Column(name = "URL", length = 300)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @OneToMany(mappedBy = "licitacion")
    public Set<Adjudicacion> getAdjudicaciones() {
        return adjudicaciones;
    }

    public void setAdjudicaciones(Set<Adjudicacion> adjudicaciones) {
        this.adjudicaciones = adjudicaciones;
    }

    @Override
    public String toString() {
        return "Licitacion{" +
                "id='" + id + '\'' +
                ", fecha='" + fecha + '\'' +
                ", fechaLimite='" + fechaLimite + '\'' +
                ", diasFechaLimite='" + diasFechaLimite + '\'' +
                ", titulo='" + titulo + '\'' +
                ", category='" + category + '\'' +
                ", sid='" + sid + '\'' +
                ", url='" + url + '\'' +
                ", adjudicaciones=" + adjudicaciones +
                '}';
    }
}
