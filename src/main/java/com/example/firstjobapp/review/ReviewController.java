package com.example.firstjobapp.review;

import com.example.firstjobapp.company.Company;
import org.apache.coyote.Response;
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
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                            @PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId, reviewId);
        if(review == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,
                                            @RequestBody Review review){
        boolean isReviewSaved =  reviewService.addReviews(companyId,review);
        if(isReviewSaved) {
            return new ResponseEntity<>("Review added successsfully",
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>("review NOT added",
                HttpStatus.NOT_FOUND);
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean isupdated = reviewService.updateReview(companyId, reviewId, review);
        if (isupdated) {
            return new ResponseEntity<>("updated Review successfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>("review NOT updated", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){
        boolean isdeleted = reviewService.deleteReview(companyId, reviewId);
        if(isdeleted){
            return new ResponseEntity<>("review DELETED successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("review NOT deleted", HttpStatus.NOT_FOUND);
    }
}
