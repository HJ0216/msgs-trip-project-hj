package com.msgs.domain.user.domain;

import com.msgs.domain.user.dto.LoginRequestDTO;
import com.msgs.global.common.model.BaseEntity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

   @Id @GeneratedValue
   @Column(name = "user_id")
   private Integer id;

   @Column(nullable = false, columnDefinition="char(1)")
   private String status;

   @Enumerated(EnumType.STRING)
   private LoginType loginType;

   private String role;

   @Column(nullable = false, unique = true, length = 50)
   private String email;

   @Column(nullable = false, unique = true, columnDefinition="char(11)")
   private String phone;

   @Column(length = 30)
   private String nickname;

   @Column(length = 30)
   private String password;

   private String imagePath;



   // 카카오 로그인 -> 회원 생성
   public static User kakaoCreate(LoginRequestDTO loginRequestDTO) {
      return User.builder()
              .status("K")
              .loginType(LoginType.KAKAO)
              .role(loginRequestDTO.getRole())
              .email(loginRequestDTO.getEmail())
              .phone("")
              .nickname(loginRequestDTO.getId().toString())
              .password(loginRequestDTO.getPassword())
              .build();
   }
}