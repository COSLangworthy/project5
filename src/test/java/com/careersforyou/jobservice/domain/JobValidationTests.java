package com.careersforyou.jobservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {

        var job = new Job("1234567890", "Title", "Author", "Blegh", "Blegh", "Blegh");

        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).isEmpty();
    }

    @Test // Run a test
    void whenIsbnDefinedButIncorrectThenValidationFails() {

        var job = new Job("a234567890", "Title", "Author", "Blegh", "Blegh", "Blegh");

        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
    }

}
