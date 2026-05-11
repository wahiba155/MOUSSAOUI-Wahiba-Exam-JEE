package ma.moussaoui.wahiba.entities;

import jakarta.persistence.*;
import lombok.Data;
import ma.moussaoui.wahiba.enums.TypePaiement;

import java.util.Date;

@Entity
@Data
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date datePaiement;

    private double montant;

    @Enumerated(EnumType.STRING)
    private TypePaiement typePaiement;

    @ManyToOne
    private ContratAssurance contrat;
}