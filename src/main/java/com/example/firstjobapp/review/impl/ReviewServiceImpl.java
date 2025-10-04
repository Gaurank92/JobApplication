package com.example.firstjobapp.review.impl;

import com.example.firstjobapp.company.Company;
import com.example.firstjobapp.company.CompanyService;
import com.example.firstjobapp.review.Review;
import com.example.firstjobapp.review.ReviewRepsitory;
import com.example.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepsitory reviewRepsitory;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepsitory reviewRepsitory,
                             CompanyService companyService) {
        this.reviewRepsitory = reviewRepsitory;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyid) {
        List<Review> reviews = reviewRepsitory.findByCompanyId(companyid);
        return reviews;
    }

    @Override
    public boolean addReviews(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepsitory.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepsitory.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId)!=null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepsitory.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId)!=null
            && reviewRepsitory.existsById(reviewId)){
            Review review = reviewRepsitory.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company, companyId);
            reviewRepsitory.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
