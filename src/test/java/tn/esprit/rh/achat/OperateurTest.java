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
    SecteurActiviteRepository secteurActiviteRepository;
    @InjectMocks
    SecteurActiviteServiceImpl Secteur;

    @Test
    public void testRetrieveAllSecteursByid() {
        SecteurActivite secteur = new SecteurActivite();
        secteur.setIdSecteurActivite(1L); // Specify the ID
        secteur.setCodeSecteurActivite("aziiz");
        secteur.setLibelleSecteurActivite("sec");


        // Mock the repository's behavior to return the sample Stock when ID 1L is passed
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(secteur));

        // Call the retrieveStock method
        SecteurActivite sec1 = Secteur.retrieveSecteurActivite(1L);

        // Assert that the retrieved stock is not null
        Assertions.assertNotNull(sec1);

        // Additional assertions if needed
        assertEquals("aziiz", sec1.getCodeSecteurActivite());
        assertEquals("sec", sec1.getLibelleSecteurActivite());


    }

    @Test
    public void testAddSecteur() {

        SecteurActivite sampleSecteur = new SecteurActivite();
        sampleSecteur.setIdSecteurActivite(1L); // Specify the ID
        sampleSecteur.setCodeSecteurActivite("secteur code");
        sampleSecteur.setLibelleSecteurActivite("sec2");


        when(secteurActiviteRepository.save(sampleSecteur)).thenReturn(sampleSecteur);


        SecteurActivite savedSecteur = Secteur.addSecteurActivite(sampleSecteur);


        assertEquals(1L, savedSecteur.getIdSecteurActivite());
        assertEquals("secteur code", savedSecteur.getCodeSecteurActivite());
        assertEquals("sec2", savedSecteur.getLibelleSecteurActivite());



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

        SecteurActivite secteur1 = new SecteurActivite();
        secteur1.setIdSecteurActivite(1L);
        secteur1.setCodeSecteurActivite("sec1");
        secteur1.setLibelleSecteurActivite("aziiz");


        SecteurActivite secteur2 = new SecteurActivite();
        secteur2.setIdSecteurActivite(2L);
        secteur2.setCodeSecteurActivite("sec2");
        secteur2.setLibelleSecteurActivite("benza");


        List<SecteurActivite> sampleSecteurList = Arrays.asList(secteur1, secteur2);


        when(secteurActiviteRepository.findAll()).thenReturn(sampleSecteurList);


        List<SecteurActivite> retrievedSecteurList = Secteur.retrieveAllSecteurActivite();


        assertEquals(2, retrievedSecteurList.size());
        assertEquals("sec1", retrievedSecteurList.get(0).getCodeSecteurActivite());
        assertEquals("sec2", retrievedSecteurList.get(1).getCodeSecteurActivite());


        verify(secteurActiviteRepository).findAll();
    }

    @Test
    public void testDeleteOperateur() {

        Secteur.deleteSecteurActivite(1L);


        verify(secteurActiviteRepository).deleteById(1L);
    }



}
