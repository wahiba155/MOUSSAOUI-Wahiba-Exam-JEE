package ma.moussaoui.wahiba.entities;

import jakarta.persistence.*;
import lombok.Data;
import ma.moussaoui.wahiba.enums.TypeLogement;

@Entity
@Data
public class ContratHabitation extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;

    private String adresseLogement;

    private double superficie;
}