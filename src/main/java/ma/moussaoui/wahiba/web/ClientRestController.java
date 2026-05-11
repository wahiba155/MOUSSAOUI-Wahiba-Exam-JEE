package ma.moussaoui.wahiba.web;

import ma.moussaoui.wahiba.dto.ClientDTO;
import ma.moussaoui.wahiba.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/api/clients")
@AllArgsConstructor
@Tag(name = "Client", description = "API de gestion des clients")
@SecurityRequirement(name = "bearerAuth")
public class ClientRestController {

    private ClientService clientService;

    // ─── GET ALL ───────────────────────────────────────────────────────────────
    @GetMapping
    @Operation(summary = "Lister tous les clients",
            description = "Retourne la liste paginée de tous les clients")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès"),
            @ApiResponse(responseCode = "403", description = "Accès refusé")
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Page<ClientDTO>> getAllClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword) {
        return ResponseEntity.ok(clientService.getAllClients(page, size, keyword));
    }

    // ─── GET BY ID ─────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(summary = "Trouver un client par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Client non trouvé")
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ClientDTO> getClientById(
            @Parameter(description = "ID du client") @PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    // ─── SEARCH ────────────────────────────────────────────────────────────────
    @GetMapping("/search")
    @Operation(summary = "Rechercher des clients par nom")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ClientDTO>> searchClients(
            @RequestParam String nom) {
        return ResponseEntity.ok(clientService.searchClientsByNom(nom));
    }

    // ─── CREATE ────────────────────────────────────────────────────────────────
    @PostMapping
    @Operation(summary = "Créer un nouveau client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.saveClient(clientDTO));
    }

    // ─── UPDATE ────────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un client")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable Long id,
            @Valid @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        return ResponseEntity.ok(clientService.updateClient(clientDTO));
    }

    // ─── DELETE ────────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    // ─── GET CONTRATS DU CLIENT ────────────────────────────────────────────────
    @GetMapping("/{id}/contrats")
    @Operation(summary = "Lister les contrats d'un client")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getContratsOfClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getContratsByClientId(id));
    }
}