package com.ipiecoles.java.java350.acceptance;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.ipiecoles.java.java350.service.EmployeService;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeAcceptanceTest {

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    EmployeService employeService;

    @Step("Soit un employé appelé <prenom> <nom> de matricule <matricule>")
    public void insertEmploye(String prenom, String nom, String matricule) {
        Employe employe = new Employe(nom, prenom, matricule,
                LocalDate.now(), 1500.0, 1, 1.0);
        employeRepository.save(employe);
    }

    @Step("J'embauche une personne appelée <prenom> <nom> diplômée d'un <diplome> en tant que <poste> à <tauxActivite>")
    public void embaucheEmploye(String prenom, String nom, String diplome, String poste, String tauxActivite) throws EmployeException {
        Double tempsPartiel = 1.0; // Plein temps
        if (tauxActivite.equals("mi-temps")) {
            tempsPartiel = 0.5;
        }
        NiveauEtude niveauEtude = NiveauEtude.valueOf(diplome.toUpperCase());
        employeService.embaucheEmploye(nom, prenom, Poste.valueOf(poste.toUpperCase()), niveauEtude, tempsPartiel);
    }

    @Step("J'obtiens bien un nouvel employé appelé <prenom> <nom> de performance <performance> portant le matricule <matricule> et touchant un salaire de <salaire>")
    public void checkEmploye(String prenom, String nom, Integer performance, String matricule, String salaire) {
        Employe employe = employeRepository.findByMatricule(matricule);
        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPerformance()).isEqualTo(performance);
    }
}
