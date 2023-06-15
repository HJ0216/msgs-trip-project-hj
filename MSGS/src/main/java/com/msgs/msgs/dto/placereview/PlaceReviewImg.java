package com.msgs.msgs.dto.placereview;

import com.msgs.msgs.dto.tripschedule.TripDetailSchedule;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="place_review_img" , indexes = @Index(name = "place_review_img_index", columnList = "seq"))
@IdClass(PlaceReviewImgId.class)
@Data
public class PlaceReviewImg {

    @Id
    private int seq;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", columnDefinition = "varchar(15)", nullable = false)
    private PlaceReview placeReview;


    @Column(name = "img_origin_name", length = 50)
    private String imgOriginName;

    @Column(name = "img_server_name", length = 50)
    private String imgServerName;

    @Column(name = "img_path", length = 30)
    private String imgPath;

    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @Column(name = "mod_date")
    private LocalDate modDate;


}