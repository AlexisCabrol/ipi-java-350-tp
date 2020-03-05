package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;

    @BeforeEach
    @AfterEach
    public void setup(){
        employeRepository.deleteAll();
    }

    @Test
    public void testFindByImmat() {
        // Given
        Employe employe = new Employe();
        employe.setMatricule("TESTMAT");
        employeRepository.save(employe);

        // When
        String findLastMatriculeResult = employeRepository.findLastMatricule();

        // Then
        Assertions.assertThat(findLastMatriculeResult).isEqualTo("ESTMAT");
    }
}