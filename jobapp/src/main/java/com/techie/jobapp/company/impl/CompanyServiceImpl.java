package com.techie.jobapp.company.impl;

import com.techie.jobapp.company.Company;
import com.techie.jobapp.company.CompanyRepository;
import com.techie.jobapp.company.CompanyService;
import com.techie.jobapp.job.Job;
import com.techie.jobapp.job.JobRepository;
import com.techie.jobapp.review.Review;
import com.techie.jobapp.review.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    CompanyRepository companyRepository;
    JobRepository jobRepository;
    ReviewRepository reviewRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository, ReviewRepository reviewRepository) {

        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
      return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteCompany(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            List<Job> jobs = company.getJobs();
            List<Review> reviews = company.getReviews();

            // Delete all associated jobs & reviews
            reviewRepository.deleteAll(reviews);
            jobRepository.deleteAll(jobs);

            // Then delete the company
            companyRepository.delete(company);

            return true;
        } else {
            return false; // Company not found
        }
    }

    @Override
    public Boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
