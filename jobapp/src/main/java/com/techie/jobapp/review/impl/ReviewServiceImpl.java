package com.techie.jobapp.review.impl;

import com.techie.jobapp.company.Company;
import com.techie.jobapp.company.CompanyService;
import com.techie.jobapp.job.Job;
import com.techie.jobapp.job.JobRepository;
import com.techie.jobapp.review.Review;
import com.techie.jobapp.review.ReviewRepository;
import com.techie.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean createReview(Long companyId, Review review) {
        Company company = companyService.findCompany(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.findCompany(companyId) != null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.findCompany(companyId) !=null){
            updatedReview.setCompany(companyService.findCompany(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;

        }else{
            return false;
        }

    }
}
