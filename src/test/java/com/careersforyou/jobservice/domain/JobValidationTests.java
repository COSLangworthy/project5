package com.careersforyou.jobservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JobValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {

        var job = new Job("1234567890", "Title", "Author", "Blegh", "Blegh", "Blegh");

        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).isEmpty();
    }

    @Test //Test whether JobId format is correct
    void whenJobIdDefinedButIncorrectThenValidationFails() {

        var job = new Job("a1", "Title", "Author", "Blegh", "Blegh", "Blegh");

        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The Job ID format must be valid.");
    }

    @Test //Test whether JobId is null
    void whenJobNotDefinedThenValidationFails(){

        var job = new Job(null, "Title", "Author", "Blegh", "Blegh", "Blegh");

        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Job ID cannot be empty");
    }
}