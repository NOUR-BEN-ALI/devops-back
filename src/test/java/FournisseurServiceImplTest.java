import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Test
    public void testAddFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCode("123");
        fournisseur.setLibelle("Fournisseur Test");

        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setDateDebutCollaboration(new Date());

        Mockito.when(detailFournisseurRepository.save(Mockito.any(DetailFournisseur.class))).thenReturn(detailFournisseur);
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur);

        Fournisseur ajoutFournisseur = fournisseurService.addFournisseur(fournisseur);

        Mockito.verify(detailFournisseurRepository).save(Mockito.any(DetailFournisseur.class));
        Mockito.verify(fournisseurRepository).save(Mockito.any(Fournisseur.class));

        assertEquals(fournisseur, ajoutFournisseur);
    }

    @Test
    public void testUpdateFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);

        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setIdDetailFournisseur(1L);
        detailFournisseur.setDateDebutCollaboration(new Date());

        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur);
        Mockito.when(detailFournisseurRepository.save(Mockito.any(DetailFournisseur.class))).thenReturn(detailFournisseur);

        Fournisseur updatedFournisseur = fournisseurService.updateFournisseur(fournisseur);

        Mockito.verify(fournisseurRepository).save(Mockito.any(Fournisseur.class));
        Mockito.verify(detailFournisseurRepository).save(Mockito.any(DetailFournisseur.class));

        assertEquals(fournisseur.getIdFournisseur(), updatedFournisseur.getIdFournisseur());
    }

}
