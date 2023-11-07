package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

class ProduitServiceImplTest {

    @InjectMocks
    private ProduitServiceImpl produitService;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllProduits() {
        // Arrange
        Produit produit1 = new Produit();
        Produit produit2 = new Produit();
        List<Produit> produits = new ArrayList<>();
        produits.add(produit1);
        produits.add(produit2);

        Mockito.when(produitRepository.findAll()).thenReturn(produits);

        // Act
        List<Produit> result = produitService.retrieveAllProduits();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void addProduit() {
        // Arrange
        Produit produit = new Produit();
        Mockito.when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // Act
        Produit addedProduit = produitService.addProduit(new Produit());

        // Assert
        assertNotNull(addedProduit);
    }

    @Test
    void deleteProduit() {
        // Arrange
        Mockito.doNothing().when(produitRepository).deleteById(anyLong());

        // Act
        produitService.deleteProduit(1L);

        // No need to assert, as we are testing void method
    }

    @Test
    void updateProduit() {
        // Arrange
        Produit updatedProduit = new Produit();
        Mockito.when(produitRepository.save(any(Produit.class))).thenReturn(updatedProduit);

        // Act
        Produit result = produitService.updateProduit(new Produit());

        // Assert
        assertNotNull(result);
        assertEquals(updatedProduit, result);
    }

    @Test
    void retrieveProduit() {
        // Arrange
        Produit produit = new Produit();
        Mockito.when(produitRepository.findById(anyLong())).thenReturn(Optional.of(produit));

        // Act
        Produit result = produitService.retrieveProduit(1L);

        // Assert
        assertNotNull(result);
        assertEquals(produit, result);
    }

    @Test
    void assignProduitToStock() {
        // Arrange
        Produit produit = new Produit();
        Stock stock = new Stock();
        Mockito.when(produitRepository.findById(anyLong())).thenReturn(Optional.of(produit));
        Mockito.when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));

        // Act
        produitService.assignProduitToStock(1L, 2L);

        // Assert
        assertEquals(stock, produit.getStock());
    }
}
