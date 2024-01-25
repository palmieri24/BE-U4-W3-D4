package it.epicode.Entities;

import it.epicode.Enums.TipoEvento;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "eventi")
@DiscriminatorColumn(name = "tipo_evento")
@NamedQueries({
        @NamedQuery(name = "Evento.getPartiteVinteInCasa", query = "SELECT p FROM PartitaDiCalcio p WHERE squadraVincente = :squadracasa"),
        @NamedQuery(name = "Evento.getPartiteVinteInTrasferta", query = "SELECT p FROM PartitaDiCalcio p WHERE squadraVincente = :squadratrasferta"),
        @NamedQuery(name = "Evento.getPartitePareggiate", query = "SELECT p FROM PartitaDiCalcio p WHERE squadraVincente IS NULL")
})
public abstract class Evento {


    @Id
    @GeneratedValue
    private long id;
    private String titolo;
    private Date dataEvento;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;
    private int numeroMassimoPartecipanti;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @OneToMany(mappedBy = "evento")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Partecipazione> partecipazione;

    public Evento() {
    }

    public Evento(String titolo, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti, Location location) {
        Date eventDate = new Date();
        this.titolo = titolo;
        this.dataEvento = eventDate;
        this.descrizione = descrizione;
        this.tipoEvento = tipoEvento;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
        this.location = location;

    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public long getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumeroMassimoPartecipanti() {
        return numeroMassimoPartecipanti;
    }

    public void setNumeroMassimoPartecipanti(int numeroMassimoPartecipanti) {
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti +
                '}';
    }
}