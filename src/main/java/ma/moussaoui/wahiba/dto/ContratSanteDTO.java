package ma.moussaoui.wahiba.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.moussaoui.wahiba.enums.NiveauCouverture;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratSanteDTO extends ContratDTO {
    private NiveauCouverture niveauCouverture;
    private int nombrePersonnesCouvertes;
}