package ma.moussaoui.wahiba.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratAutomobileDTO extends ContratDTO {
    private String numeroImmatriculation;
    private String marqueVehicule;
    private String modeleVehicule;
}