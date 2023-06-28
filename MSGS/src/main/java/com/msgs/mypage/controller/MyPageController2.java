package com.msgs.mypage.controller;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.msgs.msgs.entity.user.UserEntity;
import com.msgs.mypage.dto.MyPageUserDTO;
import com.msgs.mypage.service.MyPageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("mypage")
public class MyPageController2 {
	
	@Autowired
    private MyPageService myPageService;


	// 내 정보 뿌리기 
    @GetMapping("/getMyInfo/userId={userId}")
    public MyPageUserDTO getMyInfo() {
    	String userId = "m000003"; // Set the desired hardcoded value for id
        return myPageService.getMyInfo(userId);
    }
    
    //회원 탈퇴 
    @PostMapping("/userDelete")
    public void userDelete(){
    	String id = "m000003";
    	myPageService.userDelete(id);
    }
    
    
    
}