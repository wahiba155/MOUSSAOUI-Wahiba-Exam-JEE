package ma.moussaoui.wahiba.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class ContratAutomobile extends ContratAssurance {

    private String numeroImmatriculation;

    private String marqueVehicule;

    private String modeleVehicule;
}