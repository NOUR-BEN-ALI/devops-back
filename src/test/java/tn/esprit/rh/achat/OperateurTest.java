package tn.esprit.rh.achat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OperateurTest {
    @Mock
    OperateurRepository operateurRepository;
    @InjectMocks
    OperateurServiceImpl operateurService;

    @Test
    public void testRetrieveAllOperateursByid() {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L); // Specify the ID
        operateur.setNom("Malak");
        operateur.setPrenom("Operateur1");
        operateur.setPassword("5Arctic4");

        // Mock the repository's behavior to return the sample Stock when ID 1L is passed
        when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        // Call the retrieveStock method
        Operateur operateur1 = operateurService.retrieveOperateur(1L);

        // Assert that the retrieved stock is not null
        Assertions.assertNotNull(operateur1);

        // Additional assertions if needed
        assertEquals("Malak", operateur1.getNom());
        assertEquals("Operateur1", operateur1.getPrenom());
        assertEquals("5Arctic4", operateur1.getPassword());

    }

    @Test
    public void testAddOperateur() {

        Operateur sampleOperateur = new Operateur();
        sampleOperateur.setIdOperateur(1L); // Specify the ID
        sampleOperateur.setNom("Operateur Nom");
        sampleOperateur.setPrenom("Operateur2");
        sampleOperateur.setPassword("devops");

        when(operateurRepository.save(sampleOperateur)).thenReturn(sampleOperateur);


        Operateur savedOperateur = operateurService.addOperateur(sampleOperateur);


        assertEquals(1L, savedOperateur.getIdOperateur());
        assertEquals("Operateur Nom", savedOperateur.getNom());
        assertEquals("Operateur2", savedOperateur.getPrenom());
        assertEquals("devops", savedOperateur.getPassword());


        verify(operateurRepository).save(sampleOperateur);
    }
    @Test
    public void testUpdateOperateur() {
        // Créez un échantillon d'Operateur
        Operateur sampleOperateur = new Operateur();
        sampleOperateur.setIdOperateur(1L); // Specify the ID
        sampleOperateur.setNom("Operateur Nom");
        sampleOperateur.setPrenom("Operateur2");
        sampleOperateur.setPassword("devops");

        // Mockez le comportement de la méthode save du référentiel pour renvoyer l'échantillon d'operateur
        when(operateurRepository.save(sampleOperateur)).thenReturn(sampleOperateur);

        // Appelez la méthode updateStock
        Operateur updatedOperateur = operateurService.updateOperateur(sampleOperateur);

        // Assurez-vous que l'operateur mis à jour correspond aux valeurs attendues
        assertEquals(1L, updatedOperateur.getIdOperateur());
        assertEquals("Operateur Nom ", updatedOperateur.getNom());
        assertEquals("Operateur2", updatedOperateur.getPrenom());
        assertEquals("devops", updatedOperateur.getPassword());

        // Vérifiez que la méthode save du référentiel a été appelée
        verify(operateurRepository).save(sampleOperateur);
    }

    @Test
    public void testRetrieveAllOperateurs() {

        Operateur operateur1 = new Operateur();
        operateur1.setIdOperateur(1L);
        operateur1.setNom("Operateur1");
        operateur1.setPrenom("malak");
        operateur1.setPassword("test1");

        Operateur operateur2 = new Operateur();
        operateur2.setIdOperateur(2L);
        operateur2.setNom("Operateur2");
        operateur2.setPrenom("benmansour");
        operateur2.setPassword("test2");

        List<Operateur> sampleOperateurList = Arrays.asList(operateur1, operateur2);


        when(operateurRepository.findAll()).thenReturn(sampleOperateurList);


        List<Operateur> retrievedOperateurList = operateurService.retrieveAllOperateurs();


        assertEquals(2, retrievedOperateurList.size());
        assertEquals("Operateur1", retrievedOperateurList.get(0).getNom());
        assertEquals("Operateur2", retrievedOperateurList.get(1).getNom());


        verify(operateurRepository).findAll();
    }

    @Test
    public void testDeleteOperateur() {

        operateurService.deleteOperateur(1L);


        verify(operateurRepository).deleteById(1L);
    }



}

