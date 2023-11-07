//package tn.esprit.rh.achat.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import tn.esprit.rh.achat.entities.CategorieFournisseur;
//import tn.esprit.rh.achat.entities.Facture;
//import tn.esprit.rh.achat.entities.Fournisseur;
//import tn.esprit.rh.achat.entities.Operateur;
//import tn.esprit.rh.achat.repositories.FactureRepository;
//import tn.esprit.rh.achat.repositories.FournisseurRepository;
//import tn.esprit.rh.achat.repositories.OperateurRepository;
//import tn.esprit.rh.achat.services.FactureServiceImpl;
//import tn.esprit.rh.achat.services.FournisseurServiceImpl;
//import tn.esprit.rh.achat.services.OperateurServiceImpl;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//public class FactureTestJUnit {
//    @Autowired
//    private FactureServiceImpl factureService;
//
//    @Autowired
//    private FactureRepository factureRepository;
//
//    @Autowired
//    private OperateurRepository operateurRepository;
//
//    @Autowired
//    private FournisseurRepository fournisseurRepository;
//    @Autowired
//    FournisseurServiceImpl fournisseurService;
//    @Autowired
//    private OperateurServiceImpl operateurService;
//
//    @Test
//    void retrieveAllFacture() {
//        Facture facture = new Facture(1L, 100, 200, new Date(), new Date(), false, null, null,null);
//        Facture facture1 = new Facture(2L, 300, 400, new Date(), new Date(), false, null, null,null);
//        facture = factureRepository.save(facture);
//        facture1 = factureRepository.save(facture1);
//        List<Facture> factures = factureService.retrieveAllFactures();
//        assertNotNull(factures);
//        assertFalse(factures.isEmpty());
//        assertTrue(factures.size() >= 2);
//    }
//
//    @Test
//    void cancelFacture() {
//        // Créer des données de test
//        Facture facture = new Facture(3L, 500, 600, new Date(), new Date(), false, null, null,null);
//        facture.setArchivee(false);
//
//        // Ajouter la facture à la base de données pour les tests
//        facture = factureRepository.save(facture);
//
//        // Appel de la méthode
//        factureService.cancelFacture(facture.getIdFacture());
//
//        // Vérification
//        assertTrue(facture.getArchivee() == true);
//    }
//
//    @Test
//    void retrieveFacture() {
//        // Créer des données de test
//        Facture facture = new Facture(8L, 50, 200, new Date(), new Date(), false, null, null,null);
//        facture = factureService.addFacture(facture);
//
//        // Appel de la méthode
//        Facture result = factureService.retrieveFacture(facture.getIdFacture());
//
//        // Vérification
//        assertNotNull(result);
//        assertEquals(facture.getIdFacture(), result.getIdFacture());
//
//    }
//
//    @Test
//    void addFacture() {
//        Facture facture = new Facture(5L, 350, 600, new Date(), new Date(), false, null, null,null);
//
//        Facture invTest = factureService.addFacture(facture);
//
//        assertNotNull(invTest);
//        // Ajoutez d'autres assertions au besoin pour vérifier les propriétés de l'invoice ajouté
//    }
//
//    @Test
//    void getFacturesByfournisseur() {
//        // Créer des données de test
//        Fournisseur fournisseur = new Fournisseur(1L, "code1", "label1", CategorieFournisseur.ORDINAIRE, null, null,null);
//        fournisseur = fournisseurRepository.save(fournisseur);
//        Facture facture = new Facture(11L, 100, 200, new Date(), new Date(), false, null, fournisseur , null);
//        Facture facture1 = new Facture(12L, 300, 400, new Date(), new Date(), false, null, fournisseur,null);
//        facture = factureService.addFacture(facture);
//        facture1 = factureService.addFacture(facture1);
//
//        List<Facture> factures = new ArrayList<Facture>();
//        factures.add(facture);
//        factures.add(facture1);
//        fournisseur.setFactures(factures);
//        fournisseur = fournisseurRepository.save(fournisseur);
//
//        // Appel de la méthode
//        List<Facture> listFactures = factureService.getFacturesByFournisseur(fournisseur.getIdFournisseur());
//
//        // Vérification
//
//        assertFalse(listFactures.isEmpty());
//    }
//
//    @Test
//    void assignOperateurTofacture() {
//        // Créez des données de test
//        Operateur operateur = new Operateur();
//        operateur.setNom("nour");
//        operateur.setPrenom("benali");
//        operateur.setPassword("nour123");
//        operateur = operateurRepository.save(operateur);
//
//        Facture facture = new Facture(13L, 10, 100, new Date(), new Date(), false, null, null, null);
//        facture = factureService.addFacture(facture);
//
//        // Appel de la méthode
//        factureService.assignOperateurToFacture(operateur.getIdOperateur(), facture.getIdFacture());
//
//        // Vérification
//        Operateur updatedOperateur = operateurRepository.findById(operateur.getIdOperateur()).orElse(null);
//        Facture updatedFacture = factureRepository.findById(facture.getIdFacture()).orElse(null);
//
//        assertNotNull(updatedOperateur);
//        assertNotNull(updatedFacture);
//        assertTrue(updatedOperateur.getFactures().contains(updatedFacture));
//    }
//
//
//}
