package com.msgs.domain.tripstory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoryLikeCountDTO {

  // UserEntity
  private String storyId;

  private String userId;

//    // entity 값 DTO 생성자 주입 - TripStory entity
//    public StoryLikeCountDTO(TripStory tripStory) {
//        this.storyId = tripStory.getId();
//    }
//    
// // entity 값 DTO 생성자 주입 - TripStory entity
//    public StoryLikeCountDTO(UserEntity userEntity) {
//        this.userId = userEntity.getId();
//    }
//    
//    

}
