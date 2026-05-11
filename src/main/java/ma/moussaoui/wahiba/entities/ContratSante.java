package ma.moussaoui.wahiba.entities;

import jakarta.persistence.*;
import lombok.Data;
import ma.moussaoui.wahiba.enums.NiveauCouverture;

@Entity
@Data
public class ContratSante extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;

    private int nombrePersonnesCouvertes;
}