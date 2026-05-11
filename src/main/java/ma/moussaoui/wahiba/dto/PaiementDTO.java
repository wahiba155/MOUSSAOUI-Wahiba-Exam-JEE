package ma.moussaoui.wahiba.dto;

import lombok.Data;
import ma.moussaoui.wahiba.enums.TypePaiement;

import java.util.Date;

@Data
public class PaiementDTO {

    private Long id;
    private Date datePaiement;
    private double montant;
    private TypePaiement typePaiement;

    private Long contratId;
}