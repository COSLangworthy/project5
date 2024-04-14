package com.careersforyou.jobservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

public record Job(

        //Check if JobID is null
        @NotBlank(message = "Job ID cannot be empty")
        //Check if JodID format is correct
        @Pattern(regexp = "\\d{10}", message = "The Job ID format must be valid.")
        String jobid,
        String title,
        String description,
        String companyname,
        String skill1,
        String skill2


){

}
