package ma.moussaoui.wahiba.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)  // ✅

public class ContratAutomobile extends ContratAssurance {

    private String numeroImmatriculation;

    private String marqueVehicule;

    private String modeleVehicule;
}