package ma.moussaoui.wahiba.dto;

import lombok.Data;
import ma.moussaoui.wahiba.enums.StatutContrat;

import java.util.Date;

@Data
public class ContratDTO {

    private Long id;
    private Date dateSouscription;
    private StatutContrat statut;
    private double montantCotisation;
    private int dureeContrat;
    private double tauxCouverture;

    private Long clientId;
}