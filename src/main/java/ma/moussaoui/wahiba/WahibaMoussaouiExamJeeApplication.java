package ma.moussaoui.wahiba;

import ma.moussaoui.wahiba.entities.*;
import ma.moussaoui.wahiba.enums.StatutContrat;
import ma.moussaoui.wahiba.enums.TypePaiement;
import ma.moussaoui.wahiba.repositories.ClientRepository;
import ma.moussaoui.wahiba.repositories.ContratAssuranceRepository;
import ma.moussaoui.wahiba.repositories.PaiementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class WahibaMoussaouiExamJeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WahibaMoussaouiExamJeeApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			ClientRepository clientRepository,
			ContratAssuranceRepository contratRepository,
			PaiementRepository paiementRepository
	) {

		return args -> {

			// =========================
			// CLIENT
			// =========================

			Client client = new Client();
			client.setNom("Wahiba Moussaoui");
			client.setEmail("wahiba@gmail.com");

			clientRepository.save(client);

			// =========================
			// CONTRAT AUTOMOBILE
			// =========================

			ContratAutomobile auto = new ContratAutomobile();

			auto.setDateSouscription(new Date());
			auto.setStatut(StatutContrat.EN_COURS);
			auto.setMontantCotisation(5000);
			auto.setDureeContrat(12);
			auto.setTauxCouverture(80);

			auto.setNumeroImmatriculation("123-A-55");
			auto.setMarqueVehicule("BMW");
			auto.setModeleVehicule("X6");

			auto.setClient(client);

			contratRepository.save(auto);

			// =========================
			// CONTRAT SANTE
			// =========================

			ContratSante sante = new ContratSante();

			sante.setDateSouscription(new Date());
			sante.setStatut(StatutContrat.VALIDE);
			sante.setMontantCotisation(3000);
			sante.setDureeContrat(24);
			sante.setTauxCouverture(90);

			sante.setNombrePersonnesCouvertes(4);

			sante.setClient(client);

			contratRepository.save(sante);

			// =========================
			// PAIEMENTS
			// =========================

			Paiement paiement1 = new Paiement();

			paiement1.setDatePaiement(new Date());
			paiement1.setMontant(1000);
			paiement1.setTypePaiement(TypePaiement.MENSUALITE);
			paiement1.setContrat(auto);

			paiementRepository.save(paiement1);

			Paiement paiement2 = new Paiement();

			paiement2.setDatePaiement(new Date());
			paiement2.setMontant(3000);
			paiement2.setTypePaiement(TypePaiement.PAIEMENT_ANNUEL);
			paiement2.setContrat(sante);

			paiementRepository.save(paiement2);

			// =========================
			// AFFICHAGE
			// =========================

			System.out.println("==================================");
			System.out.println(" APPLICATION DE GESTION ASSURANCE ");
			System.out.println("==================================");

			contratRepository.findAll().forEach(c -> {

				System.out.println("----------------------------------");
				System.out.println("Contrat ID : " + c.getId());
				System.out.println("Client : " + c.getClient().getNom());
				System.out.println("Statut : " + c.getStatut());
				System.out.println("Cotisation : " + c.getMontantCotisation());

				if (c instanceof ContratAutomobile a) {

					System.out.println("Type : Assurance Automobile");
					System.out.println("Vehicule : " +
							a.getMarqueVehicule() + " " +
							a.getModeleVehicule());
				}

				if (c instanceof ContratSante s) {

					System.out.println("Type : Assurance Santé");
					System.out.println("Personnes couvertes : " +
							s.getNombrePersonnesCouvertes());
				}
			});

			System.out.println("==================================");
			System.out.println("TEST DAO EFFECTUE AVEC SUCCES");

			System.out.println("==================================");

		};
	}
	}
