package ma.moussaoui.wahiba.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.moussaoui.wahiba.enums.NiveauCouverture;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)  // ✅

public class ContratSante extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;

    private int nombrePersonnesCouvertes;
}