package ma.moussaoui.wahiba.web;

import ma.moussaoui.wahiba.dto.PaiementDTO;
import ma.moussaoui.wahiba.enums.TypePaiement;
import ma.moussaoui.wahiba.services.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@AllArgsConstructor
@Tag(name = "Paiement", description = "API de gestion des paiements de contrats")
@SecurityRequirement(name = "bearerAuth")
public class PaiementRestController {

    private PaiementService paiementService;

    // ─── GET ALL ───────────────────────────────────────────────────────────────
    @GetMapping
    @Operation(summary = "Lister tous les paiements")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<PaiementDTO>> getAllPaiements() {
        return ResponseEntity.ok(paiementService.getAllPaiements());
    }

    // ─── GET BY ID ─────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un paiement par ID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PaiementDTO> getPaiementById(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.getPaiementById(id));
    }

    // ─── GET BY CONTRAT ────────────────────────────────────────────────────────
    @GetMapping("/contrat/{contratId}")
    @Operation(summary = "Lister les paiements d'un contrat")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<PaiementDTO>> getPaiementsByContrat(@PathVariable Long contratId) {
        return ResponseEntity.ok(paiementService.getPaiementsByContratId(contratId));
    }

    // ─── GET BY TYPE ───────────────────────────────────────────────────────────
    @GetMapping("/type/{type}")
    @Operation(summary = "Filtrer les paiements par type",
            description = "Types possibles : MENSUALITE, PAIEMENT_ANNUEL, PAIEMENT_EXCEPTIONNEL")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<PaiementDTO>> getPaiementsByType(@PathVariable TypePaiement type) {
        return ResponseEntity.ok(paiementService.getPaiementsByType(type));
    }

    // ─── TOTAL PAR CONTRAT ─────────────────────────────────────────────────────
    @GetMapping("/contrat/{contratId}/total")
    @Operation(summary = "Calculer le total des paiements d'un contrat")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Double> getTotalPaiementsParContrat(@PathVariable Long contratId) {
        return ResponseEntity.ok(paiementService.getTotalPaiementsParContrat(contratId));
    }

    // ─── CREATE ────────────────────────────────────────────────────────────────
    @PostMapping
    @Operation(summary = "Enregistrer un nouveau paiement")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paiement enregistré"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Contrat non trouvé")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaiementDTO> createPaiement(@Valid @RequestBody PaiementDTO paiementDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paiementService.savePaiement(paiementDTO));
    }

    // ─── UPDATE ────────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaiementDTO> updatePaiement(
            @PathVariable Long id,
            @Valid @RequestBody PaiementDTO paiementDTO) {
        paiementDTO.setId(id);
        return ResponseEntity.ok(paiementService.updatePaiement(paiementDTO));
    }

    // ─── DELETE ────────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}