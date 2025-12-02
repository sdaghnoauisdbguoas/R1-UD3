package Package.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ESTADISTICA")
public class Estadistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VICTORIAS")
    private int victorias;

    @Column(name = "DERROTAS")
    private int derrotas;

    @ManyToOne
    @JoinColumn(name = "PELEADOR_ID")
    private Peleador peleador;

    public Estadistica() {}
    public Estadistica(int victorias, int derrotas) {
        this.victorias = victorias;
        this.derrotas = derrotas;
    }

    public Long getId() { return id; }
    public int getVictorias() { return victorias; }
    public void setVictorias(int victorias) { this.victorias = victorias; }
    public int getDerrotas() { return derrotas; }
    public void setDerrotas(int derrotas) { this.derrotas = derrotas; }
    public Peleador getPeleador() { return peleador; }
    public void setPeleador(Peleador peleador) { this.peleador = peleador; }

    @Override
    public String toString() {
        return "Estadistica [id=" + id + ", victorias=" + victorias + ", derrotas=" + derrotas + "]";
    }
}
