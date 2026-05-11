package ma.moussaoui.wahiba.web;

import ma.moussaoui.wahiba.dto.ContratDTO;
import ma.moussaoui.wahiba.dto.ContratAutomobileDTO;
import ma.moussaoui.wahiba.dto.ContratHabitationDTO;
import ma.moussaoui.wahiba.dto.ContratSanteDTO;
import ma.moussaoui.wahiba.enums.StatutContrat;
import ma.moussaoui.wahiba.services.ContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@AllArgsConstructor
@Tag(name = "Contrat", description = "API de gestion des contrats d'assurance")
@SecurityRequirement(name = "bearerAuth")
public class ContratRestController {

    private ContratService contratService;

    // ─── GET ALL (paginé) ──────────────────────────────────────────────────────
    @GetMapping
    @Operation(summary = "Lister tous les contrats avec pagination")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Page<ContratDTO>> getAllContrats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(contratService.getAllContrats(page, size));
    }

    // ─── GET BY ID ─────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un contrat par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat trouvé"),
            @ApiResponse(responseCode = "404", description = "Contrat non trouvé")
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ContratDTO> getContratById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.getContratById(id));
    }

    // ─── GET BY STATUT ─────────────────────────────────────────────────────────
    @GetMapping("/statut/{statut}")
    @Operation(summary = "Filtrer les contrats par statut",
            description = "Statuts possibles : EN_COURS, VALIDE, RESILIE")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ContratDTO>> getContratsByStatut(
            @Parameter(description = "Statut du contrat") @PathVariable StatutContrat statut) {
        return ResponseEntity.ok(contratService.getContratsByStatut(statut));
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  CONTRAT AUTOMOBILE
    // ════════════════════════════════════════════════════════════════════════════

    @GetMapping("/automobile")
    @Operation(summary = "Lister tous les contrats automobile")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ContratAutomobileDTO>> getAllAutomobile() {
        return ResponseEntity.ok(contratService.getAllContratsAutomobile());
    }

    @PostMapping("/automobile")
    @Operation(summary = "Créer un contrat d'assurance automobile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contrat automobile créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContratAutomobileDTO> createAutomobile(
            @Valid @RequestBody ContratAutomobileDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contratService.saveContratAutomobile(dto));
    }

    @PutMapping("/automobile/{id}")
    @Operation(summary = "Modifier un contrat automobile")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContratAutomobileDTO> updateAutomobile(
            @PathVariable Long id,
            @Valid @RequestBody ContratAutomobileDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(contratService.updateContratAutomobile(dto));
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  CONTRAT HABITATION
    // ════════════════════════════════════════════════════════════════════════════

    @GetMapping("/habitation")
    @Operation(summary = "Lister tous les contrats habitation")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ContratHabitationDTO>> getAllHabitation() {
        return ResponseEntity.ok(contratService.getAllContratsHabitation());
    }

    @PostMapping("/habitation")
    @Operation(summary = "Créer un contrat d'assurance habitation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContratHabitationDTO> createHabitation(
            @Valid @RequestBody ContratHabitationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contratService.saveContratHabitation(dto));
    }

    @PutMapping("/habitation/{id}")
    @Operation(summary = "Modifier un contrat habitation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContratHabitationDTO> updateHabitation(
            @PathVariable Long id,
            @Valid @RequestBody ContratHabitationDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(contratService.updateContratHabitation(dto));
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  CONTRAT SANTE
    // ════════════════════════════════════════════════════════════════════════════

    @GetMapping("/sante")
    @Operation(summary = "Lister tous les contrats santé")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ContratSanteDTO>> getAllSante() {
        return ResponseEntity.ok(contratService.getAllContratsSante());
    }

    @PostMapping("/sante")
    @Operation(summary = "Créer un contrat d'assurance santé")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContratSanteDTO> createSante(
            @Valid @RequestBody ContratSanteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contratService.saveContratSante(dto));
    }

    @PutMapping("/sante/{id}")
    @Operation(summary = "Modifier un contrat santé")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContratSanteDTO> updateSante(
            @PathVariable Long id,
            @Valid @RequestBody ContratSanteDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(contratService.updateContratSante(dto));
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  ACTIONS MÉTIER
    // ════════════════════════════════════════════════════════════════════════════

    @PatchMapping("/{id}/valider")
    @Operation(summary = "Valider un contrat (statut → VALIDE)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContratDTO> validerContrat(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.validerContrat(id));
    }

    @PatchMapping("/{id}/resilier")
    @Operation(summary = "Résilier un contrat (statut → RESILIE)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContratDTO> resilierContrat(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.resilierContrat(id));
    }

    // ─── DELETE ────────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }
}