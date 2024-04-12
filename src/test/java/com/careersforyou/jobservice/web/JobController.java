package com.careersforyou.jobservice.web;
import com.careersforyou.jobservice.domain.Job;
import com.careersforyou.jobservice.domain.JobAlreadyExistsException;
import com.careersforyou.jobservice.domain.JobNotFoundException;
import com.careersforyou.jobservice.domain.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {

        this.jobService = jobService;
    }

    @GetMapping                 // Base handler
    public Iterable<Job> get() {

        return jobService.viewJobList();
    }

    @GetMapping("{jobid}")      // /job/{jobid}
    public Job getByJobid(@PathVariable String jobid) {

        return jobService.viewJobDetails(jobid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)     // Returns 201 if job is created successfully
    public Job post(@RequestBody Job job) {
        return jobService.addJobToDatabase(job);
    }

    @PutMapping("{jobid}")
    @ResponseStatus(HttpStatus.OK)
    public Job updateJob(@PathVariable String jobid, @RequestBody Job updatedJob){

        try{
            return jobService.editJobDetails(jobid, updatedJob);
        } catch(JobNotFoundException e){

        throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Job Not  Found, e");
        }
    }

@ExceptionHandler(JobAlreadyExistsException.class)
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)    // Returns 422 if job already exists
String JobAlreadyExists(JobAlreadyExistsException ex){
    return ex.getMessage();
}
@ExceptionHandler(JobNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)    // Returns 422 if job already exists
    String JobNotFound(JobNotFoundException ex){
        return ex.getMessage();
    }

    @DeleteMapping("{jobid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns 204 if job is deleted successfully
    public void delete(@PathVariable String jobid) {

        jobService.removeJobFromDatabase(jobid);
    }
}
