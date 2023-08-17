package com.example.authentification;

import com.example.authentification.entity.Utilisateur;
import com.example.authentification.repository.UtilisateurRepository;
import com.example.authentification.service.UtilisateurService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class AuthentificationApplicationTests {
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Test
    public void shouldAddNewUtilisateurWhenValidData(){
        Utilisateur utilisateur = new Utilisateur("clement","1234");
        Mockito.when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        Utilisateur utilisateur1 = utilisateurService.enregistrerUtilisateur("clement","1234");

        Assertions.assertEquals(utilisateur1,utilisateur);
    }

    @Test
    public void shouldReturnUtilisateurById(){
        Utilisateur utilisateur = new Utilisateur(1L,"Clement","1234");
        Optional<Utilisateur> utilisateur1 = Optional.of(utilisateur);
        Mockito.when(utilisateurRepository.findById(1L)).thenReturn(utilisateur1);
        Assertions.assertEquals(utilisateur,utilisateurService.trouverParId(1L));
    }

    @Test
    public void shouldReturnUtilisateurByUsername(){
        Utilisateur utilisateur = new Utilisateur(1L,"Clement","1234");
        Optional<Utilisateur> utilisateur1 = Optional.of(utilisateur);
        Mockito.when(utilisateurRepository.findByUsername("Clement")).thenReturn(utilisateur1);
        Assertions.assertEquals(utilisateur,utilisateurService.trouverParUsername("Clement"));
    }

    @Test
    public void shouldReturnExceptionWhenUtilisateurByIdNotFound(){
        Utilisateur utilisateur = new Utilisateur(1L,"Clement","1234");
        Mockito.when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        Long id = 100L;
        Assertions.assertThrowsExactly(RuntimeException.class,()->{
            utilisateurService.trouverParId(id);
        });
    }

    @Test
    public void shouldReturnExceptionWhenUtilisateurByUsernameNotFound(){
        Utilisateur utilisateur = new Utilisateur(1L,"Clement","1234");
        Mockito.when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        Assertions.assertThrowsExactly(RuntimeException.class,()->{
            utilisateurService.trouverParUsername("test");
        });
    }

}
