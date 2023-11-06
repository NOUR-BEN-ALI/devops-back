package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

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
    OperateurRepository secteurActiviteRepository;
    @InjectMocks
    OperateurServiceImpl Secteur;

    @Test
    public void testRetrieveAllOperateurByid() {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L); // Specify the ID
        operateur.setNom("malak");
        operateur.setPrenom("benmansour");
        operateur.setPassword("mdp");



        // Mock the repository's behavior to return the sample Stock when ID 1L is passed
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(operateur));

        // Call the retrieveStock method
        Operateur sec1 = Secteur.retrieveOperateur(1L);

        // Assert that the retrieved stock is not null
        Assertions.assertNotNull(sec1);

        // Additional assertions if needed
        assertEquals("malak", sec1.getNom());
        assertEquals("benmansour", sec1.getPrenom());
        assertEquals("mdp", sec1.getPassword());


    }

    @Test
    public void testAddOperateur() {

        Operateur sampleSecteur = new Operateur();
        sampleSecteur.setIdOperateur(1L); // Specify the ID
        sampleSecteur.setNom("nom");
        sampleSecteur.setPrenom("prenom");
        sampleSecteur.setPassword("password");

        when(secteurActiviteRepository.save(sampleSecteur)).thenReturn(sampleSecteur);


        Operateur savedSecteur = Secteur.addOperateur(sampleSecteur);


        assertEquals(1L, savedSecteur.getIdOperateur());
        assertEquals("nome", savedSecteur.getNom());
        assertEquals("prenom", savedSecteur.getPrenom());
        assertEquals("password", savedSecteur.getPassword());



        verify(secteurActiviteRepository).save(sampleSecteur);
    }
   /* @Test
    public void testUpdateSecteur() {
        // Créez un échantillon d'Operateur
        SecteurActivite sampleSecteur= new SecteurActivite();
        sampleSecteur.setIdSecteurActivite(1L); // Specify the ID
        sampleSecteur.setCodeSecteurActivite("sec Nom");
        sampleSecteur.setLibelleSecteurActivite("sec2");


        // Mockez le comportement de la méthode save du référentiel pour renvoyer l'échantillon d'operateur
        when(secteurActiviteRepository.save(sampleSecteur)).thenReturn(sampleSecteur);

        // Appelez la méthode updateStock
        SecteurActivite updateSecteurActivite = Secteur.updateSecteurActivite(sampleSecteur);

        // Assurez-vous que l'operateur mis à jour correspond aux valeurs attendues
        assertEquals(1L, updateSecteurActivite.getIdSecteurActivite());
        assertEquals("sec Nom ", updateSecteurActivite.getCodeSecteurActivite());
        assertEquals("sec2", updateSecteurActivite.getLibelleSecteurActivite());


        // Vérifiez que la méthode save du référentiel a été appelée
        verify(secteurActiviteRepository).save(sampleSecteur);
    }
*/
    @Test
    public void testRetrieveAllSecteurs() {

        Operateur secteur1 = new Operateur();
        secteur1.setIdOperateur(1L);
        secteur1.setNom("opera");
        secteur1.setPrenom("aziiz");
        secteur1.setPassword("malak");


        Operateur secteur2 = new Operateur();
        secteur2.setIdOperateur(2L);
        secteur2.setNom("opera2");
        secteur2.setPrenom("aziiz2");
        secteur2.setPassword("malak2");


        List<Operateur> sampleSecteurList = Arrays.asList(secteur1, secteur2);


        when(secteurActiviteRepository.findAll()).thenReturn(sampleSecteurList);


        List<Operateur> retrievedSecteurList = Secteur.retrieveAllOperateurs();


        assertEquals(2, retrievedSecteurList.size());
        assertEquals("opera", retrievedSecteurList.get(0).getNom());
        assertEquals("opera2", retrievedSecteurList.get(1).getNom());


        verify(secteurActiviteRepository).findAll();
    }

    @Test
    public void testDeleteOperateur() {

        Secteur.deleteOperateur(1L);


        verify(secteurActiviteRepository).deleteById(1L);
    }



}
