package com.techie.jobapp.review;

import java.util.List;


public interface ReviewService {

        List<Review> getAllReview(Long companyId);
        Review getReview(Long companyId, Long reviewId);
        Boolean createReview(Long companyId, Review review);

        Boolean deleteReview(Long companyId, Long reviewId);

        Boolean updateReview(Long companyId,Long reviewId, Review updatedReview);

}
