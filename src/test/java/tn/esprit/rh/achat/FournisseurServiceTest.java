package tn.esprit.rh.achat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FournisseurServiceTest{

    @InjectMocks
    FournisseurServiceImpl FournisseurService;

    @Mock
    FournisseurRepository FournisseurRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // initialize mocks

        // Test data setup
        Fournisseur Fournisseur1 = new Fournisseur();

        Fournisseur1.setCode("code 1");

        Fournisseur Fournisseur2 = new Fournisseur();

        Fournisseur2.setLibelle("libelle 2");

        // Mock behavior for the repository
        when(FournisseurRepository.findAll()).thenReturn(Arrays.asList(Fournisseur1, Fournisseur2));
        when(FournisseurRepository.save(any(Fournisseur.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRetrieveAllFournisseurs() {
        FournisseurService.retrieveAllFournisseurs();
    }

}