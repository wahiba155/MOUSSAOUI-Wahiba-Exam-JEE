package ma.moussaoui.wahiba.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.moussaoui.wahiba.enums.TypeLogement;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratHabitationDTO extends ContratDTO {
    private TypeLogement typeLogement;
    private String adresseLogement;
    private double superficie;
}