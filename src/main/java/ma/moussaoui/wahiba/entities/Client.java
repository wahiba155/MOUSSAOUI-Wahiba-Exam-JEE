package ma.moussaoui.wahiba.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String email;

    @OneToMany(mappedBy = "client")
    private List<ContratAssurance> contrats;
}