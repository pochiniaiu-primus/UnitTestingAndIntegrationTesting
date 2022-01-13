package com.serhiihonchar.unitandintegrationtesting.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        //given
        String email = "serhii@honchar.com";
        Student student = new Student("Serhii", email, Gender.MALE);
        underTest.save(student);
        //when
        Boolean expected = underTest.selectExistsEmail(email);
        //then
        assertThat(expected).isTrue();

    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExists() {
        //given
        String email = "serhii@honchar.com";
        //when
        Boolean expected = underTest.selectExistsEmail(email);
        //then
        assertThat(expected).isFalse();
    }
}