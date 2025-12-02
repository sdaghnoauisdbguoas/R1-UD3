package Package.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PELEADOR")
public class Peleador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ESTILO")
    private String estilo;

    @OneToMany(mappedBy = "peleador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estadistica> estadisticas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "PELEADOR_EVENTO",
        joinColumns = @JoinColumn(name = "PELEADOR_ID"),
        inverseJoinColumns = @JoinColumn(name = "EVENTO_ID")
    )
    private List<Evento> eventos = new ArrayList<>();

    public Peleador() {}

    public Peleador(String nombre, String estilo) {
        this.nombre = nombre;
        this.estilo = estilo;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEstilo() { return estilo; }
    public void setEstilo(String estilo) { this.estilo = estilo; }
    public List<Estadistica> getEstadisticas() { return estadisticas; }
    public List<Evento> getEventos() { return eventos; }

    @Override
    public String toString() {
        return "Peleador [id=" + id + ", nombre=" + nombre + ", estilo=" + estilo + "]";
    }
}
