package com.careersforyou.jobservice.persistence;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import com.careersforyou.jobservice.domain.Job;
import com.careersforyou.jobservice.domain.JobRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryJobRepository implements JobRepository {

    private static final Map<String, Job> jobs = new ConcurrentHashMap<>();

    @Override
    public Iterable<Job> findAll() { return jobs.values();}

    @Override
    public Optional<Job> findByJobid(String jobid) {

        return existsByJobid(jobid) ? Optional.of(jobs.get(jobid)) : Optional.empty();
    }

    @Override
    public boolean existsByJobid(String jobid) {

        return jobs.get(jobid) != null;
    }

    @Override
    public Job save(Job job) {

        jobs.put(job.jobid(), job);

        return job;
    }

    @Override
    public void deleteByJobid(String jobid) {

        jobs.remove(jobid);
    }
}
