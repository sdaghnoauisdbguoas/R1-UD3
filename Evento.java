package Package.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EVENTO")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @ManyToMany(mappedBy = "eventos")
    private List<Peleador> peleadores = new ArrayList<>();

    public Evento() {}
    public Evento(String nombre) { this.nombre = nombre; }

    // Getters y setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Peleador> getPeleadores() { return peleadores; }

    @Override
    public String toString() {
        return "Evento [id=" + id + ", nombre=" + nombre + "]";
    }
}
