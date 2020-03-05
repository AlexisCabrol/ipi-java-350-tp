package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository employeRepository;

    @BeforeEach
    @AfterEach
    public void setup(){
        employeRepository.deleteAll();
    }

    @Test
    void findLastMatriculeNull() {
        //Given

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertNull(lastMatricule);
    }

    @Test
    void findLastMatriculeNominal() {
        //Given
        employeRepository.save(new Employe("Doe","John","C12345", LocalDate.now(),1500.0,1,1.0));
        employeRepository.save(new Employe("Doe","John","T02345", LocalDate.now(),1500.0,1,1.0));

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertEquals("12345",lastMatricule);
    }

    @Test
    void findByMatricule() {
    }

    @Test
    void avgPerformanceWhereMatriculeStartsWith() {
    }
}