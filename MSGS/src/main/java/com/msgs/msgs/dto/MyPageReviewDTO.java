package com.msgs.msgs.dto;

import com.msgs.msgs.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.msgs.msgs.entity.user.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageReviewDTO {
    // Entity
    // User
    private int userId;

    // Like
    private String likeId;

    // place review
    private Integer reviewId;
    private String comments; // 내용
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    
    public MyPageReviewDTO(User user, Review review){
        this.userId = user.getId();
        this.reviewId = review.getId();
        this.comments = review.getComments();
        this.createdDate = review.getCreatedDate();
        this.updatedDate = review.getUpdatedDate();
    }


}
