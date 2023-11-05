package tn.esprit.rh.achat;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.IOperateurService;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OperateurTest {
    @Autowired
    IOperateurService OperateurService;
    @Mock
    OperateurRepository operateurRepository;
    @InjectMocks
    OperateurServiceImpl operateurService;




    Operateur operateur = new Operateur(1L,"malak","operateur1","arctic");

    ArrayList<Operateur> listProduits = new ArrayList<Operateur>() {
        {
            add(new Operateur(1L, "malak", "operateur1","arctic"));
            add(new Operateur(2L, "test", "operateur2","devops"));
        }

    };


    @Test
    @Order(1)

    public void testRetrieveProduit() {
        Mockito.when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(operateur));
        Operateur operateur1 = operateurService.retrieveOperateur(6L);
        Assertions.assertNotNull(operateur1);

    }

    @Test
    @Order(2)
    public void testAddProduit() throws ParseException {
        Operateur opera= new Operateur(1L, "malak", "operateur1","arctic");

        Operateur saveopera = OperateurService.addOperateur(opera);
        assertEquals(opera.getIdOperateur(), saveopera.getIdOperateur());

    }

//


    @Test
    @Order(4)
    public void testDeleteProduit() {
        OperateurService.deleteOperateur(5L);
        assertNull(OperateurService.retrieveOperateur(4L));
    }

}
