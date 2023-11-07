package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FactureTestMockito {
    @Mock
    private FactureRepository factureRepository;

    @Mock
    private OperateurRepository operateurRepository;

    @Mock
    private FournisseurRepository supplierRepository;

    @InjectMocks
    private FactureServiceImpl factureService;

    @Test
    void addInvoice() {
        Facture facture = new Facture(2L, 100.0f, 200.0f, new Date(2023, 9, 10), new Date(2023, 10, 14), false, null, null, null);
        Mockito.when(factureRepository.save(Mockito.any(Facture.class))).thenReturn(facture);

        Facture FactureTest = factureService.addFacture(facture);
        Assertions.assertNotNull(FactureTest);
    }

    @Test
    public void retrieveAllInvoices() {
        // Créez des données de test
        List<Facture> factures = new ArrayList<>();
        factures.add(new Facture(1L, 100.0f, 200.0f, new Date(2023, 9, 10), new Date(2023, 10, 14), false, null, null, null));
        factures.add(new Facture(2L, 200.0f, 200.0f, new Date(2023, 9, 10), new Date(2023, 10, 14), false, null, null, null));
        when(factureRepository.findAll()).thenReturn(factures);

        // Appel de la méthode
        List<Facture> retrievedFactures = factureService.retrieveAllFactures();

        // Vérification
        assertNotNull(retrievedFactures);
        assertEquals(2, retrievedFactures.size());
        verify(factureRepository, times(1)).findAll();
    }

    @Test
    public void retrieveInvoice() {
        Long factureId = 1L;
        Facture facture = new Facture(1L, 100.0f, 200.0f, new Date(2023, 9, 10), new Date(2023, 10, 14), false, null, null, null); // Créez un objet facture factice
        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        Facture retrievedFacture = factureService.retrieveFacture(factureId);

        assertEquals(facture, retrievedFacture);
    }

    @Test
    public void cancelInvoice() {
        Long favtureId = 1L;
        Facture facture = new Facture(1L, 100.0f, 200.0f, new Date(2023, 9, 10), new Date(2023, 10, 14), false, null, null, null); // Créez un objet factice
        when(factureRepository.findById(favtureId)).thenReturn(Optional.of(facture));

        factureService.cancelFacture(favtureId);

        assertTrue(facture.getArchivee());
        verify(factureRepository).save(facture);
    }



//    @Test
//    public void getInvoicesBySupplier() {
//        Long FournissuerId = 1L;
//
//        // Créez un objet Supplier factice avec des factures associées
//        Fournisseur mockFournissuer = new Fournisseur(1L, "code1", "label1", CategorieFournisseur.ORDINAIRE, null, null,null);
//        mockFournissuer.setFactures(List.of(new Facture(1L, 100.0f, 200.0f, new Date(2023, 7, 14), new Date(2023, 10, 14), false, null, null,null),
//                new Facture(2L, 300.0f, 400.0f, new Date(2023, 9, 10), new Date(2023, 10, 14), false, null, null,null)));
//
//        when(supplierRepository.findById(FournissuerId)).thenReturn(Optional.of(mockFournissuer));
//
//        List<Facture> result = factureService.getFacturesByFournisseur(FournissuerId);
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(supplierRepository, times(1)).findById(FournissuerId);
//    }
//    @Test
//    public void assignOperatorToInvoice() {
//        Long operateurId = 1L;
//        Long FactureId = 2L;
//
//        // Créez un objet Invoice factice
//        Facture mockInvoice = new Facture(2L, 100.0f, 200.0f, new Date(),new Date(), false, null, null,null);
//
//        // Créez un objet Operator factice
//        Operateur mockOperateurr = new Operateur(1L,"yosra", "elbich", "yossra123",null);
//
//        when(factureRepository.findById(FactureId)).thenReturn(Optional.of(mockInvoice));
//        when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(mockOperateurr));
//
//        factureService.assignOperateurToFacture(operateurId, FactureId);
//
//        // Vérifiez que la méthode save a été appelée pour sauvegarder l'opérateur
//        verify(operateurRepository, times(1)).save(mockOperateurr);
//    }


//    @Test
//    public void getTotalAmountInvoiceBetweenDates() {
//        Date startDate = new Date(2023,9,22);
//        Date endDate = new Date(2023,10,14);
//        float expectedTotalAmount = 100.0f;
//        when(factureRepository.getTotalFacturesEntreDeuxDates(startDate, endDate)).thenReturn(expectedTotalAmount);
//
//        float totalAmount = factureService.getFacturesByFournisseur(startDate, endDate);
//
//        assertEquals(expectedTotalAmount, totalAmount, 0.01); // Utilisez une tolérance appropriée
//        verify(factureRepository).getFactureByFournisseur(fournisseur);
//    }
}

