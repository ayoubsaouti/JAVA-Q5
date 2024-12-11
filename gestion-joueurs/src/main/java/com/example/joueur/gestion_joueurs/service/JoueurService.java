package com.example.joueur.gestion_joueurs.service;

import com.example.joueur.gestion_joueurs.dto.JoueurDTO;
import com.example.joueur.gestion_joueurs.dto.AmiRequest;
import com.example.joueur.gestion_joueurs.dto.AmiDTO;
import com.example.joueur.gestion_joueurs.entity.Joueur;
import com.example.joueur.gestion_joueurs.entity.Ami;
import com.example.joueur.gestion_joueurs.repository.JoueurRepository;
import com.example.joueur.gestion_joueurs.repository.AmiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoueurService {

    private final JoueurRepository joueurRepository;
    private final AmiRepository amiRepository;

    public JoueurService(JoueurRepository joueurRepository, AmiRepository amiRepository) {
        this.joueurRepository = joueurRepository;
        this.amiRepository = amiRepository;
    }

    // Ajouter un nouveau joueur
    public JoueurDTO ajouterJoueur(JoueurDTO joueurDTO) {
        Joueur joueur = new Joueur();
        joueur.setNom(joueurDTO.getNom());
        joueur.setPseudonyme(joueurDTO.getPseudonyme());
        joueur.setEmail(joueurDTO.getEmail());
        joueur.setNiveau(joueurDTO.getNiveau());
        joueur.setPointsTotaux(joueurDTO.getPointsTotaux());

        Joueur savedJoueur = joueurRepository.save(joueur);
        return convertToDTO(savedJoueur); // Utilisation de la méthode de conversion
    }

    // Obtenir un joueur par son ID
    public JoueurDTO obtenirJoueur(Long id) {
        Joueur joueur = joueurRepository.findById(id).orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        return convertToDTO(joueur); // Utilisation de la méthode de conversion
    }

    // Mettre à jour un joueur
    public JoueurDTO mettreAJourJoueur(Long id, JoueurDTO joueurDTO) {
        Joueur joueur = joueurRepository.findById(id).orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        joueur.setNom(joueurDTO.getNom());
        joueur.setPseudonyme(joueurDTO.getPseudonyme());
        joueur.setEmail(joueurDTO.getEmail());
        joueur.setNiveau(joueurDTO.getNiveau());
        joueur.setPointsTotaux(joueurDTO.getPointsTotaux());

        Joueur updatedJoueur = joueurRepository.save(joueur);
        return convertToDTO(updatedJoueur); // Utilisation de la méthode de conversion
    }

    // Supprimer un joueur
    @Transactional
    public void supprimerJoueur(Long id) {
        Joueur joueur = joueurRepository.findById(id).orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        joueurRepository.delete(joueur);
    }

    // Ajouter un ami à un joueur
    @Transactional
    public void ajouterAmi(Long idJoueur, Long idAmi) {
        Joueur joueur = joueurRepository.findById(idJoueur).orElseThrow(() -> new RuntimeException("Joueur non trouvé"));
        Joueur ami = joueurRepository.findById(idAmi).orElseThrow(() -> new RuntimeException("Ami non trouvé"));

        Ami amiEntity = new Ami();
        amiEntity.setJoueur(joueur);
        amiEntity.setAmi(ami);

        amiRepository.save(amiEntity);
    }

    // Récupérer les amis d'un joueur
    public List<AmiDTO> recupererAmis(Long id) {
        List<Ami> amis = amiRepository.findByJoueurId(id);
        return amis.stream()
                .map(ami -> new AmiDTO(ami.getAmi().getId(), ami.getAmi().getPseudonyme()))
                .collect(Collectors.toList());
    }

    // Méthode de conversion pour Joueur -> JoueurDTO
    private JoueurDTO convertToDTO(Joueur joueur) {
        return new JoueurDTO(
            joueur.getId(),
            joueur.getNom(),
            joueur.getPseudonyme(),
            joueur.getEmail(),
            joueur.getNiveau(),
            joueur.getPointsTotaux()
        );
    }
}
