package com.techie.jobapp.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> postReview(@PathVariable Long companyId, @RequestBody Review review){
        Boolean createResponse = reviewService.createReview(companyId, review);
        if(createResponse)
        {
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);

        }
        return new ResponseEntity<>("Company does not exist", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId, reviewId);
        if(review != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        Boolean deleteResponse = reviewService.deleteReview(companyId, reviewId);
        if(deleteResponse){
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("InvalreviewId reviewId", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody  Review review){
        Boolean updateResponse = reviewService.updateReview(companyId, reviewId, review);
        if(updateResponse){
            return new ResponseEntity<>("Update Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("InvalreviewId reviewId", HttpStatus.BAD_REQUEST);

    }
}
