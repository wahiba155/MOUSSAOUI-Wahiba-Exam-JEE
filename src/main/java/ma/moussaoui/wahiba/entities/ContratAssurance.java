package ma.moussaoui.wahiba.entities;

import jakarta.persistence.*;
import lombok.Data;
import ma.moussaoui.wahiba.enums.StatutContrat;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class ContratAssurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateSouscription;

    @Enumerated(EnumType.STRING)
    private StatutContrat statut;

    @Temporal(TemporalType.DATE)
    private Date dateValidation;

    private double montantCotisation;

    private int dureeContrat;

    private double tauxCouverture;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "contrat")
    private List<Paiement> paiements;
}