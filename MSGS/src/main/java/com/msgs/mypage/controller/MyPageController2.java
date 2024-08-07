package com.msgs.mypage.controller;


import java.util.Map;

import com.msgs.msgs.jwt.controller.JwtController;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msgs.mypage.dto.MyPageUserDTO;
import com.msgs.mypage.service.MyPageService;

@RestController
@RequestMapping("mypage")
public class MyPageController2 {
	
	@Autowired
    private MyPageService myPageService;
	
    @Autowired
    private JwtController userController2;

    public JSONObject getUserInfo(String token) {
        ResponseEntity<?> userInfoResponse = userController2.getUserInfo(token);
        JSONObject userInfo = new JSONObject((String) userInfoResponse.getBody());
        return userInfo;
    }
	
	// 내 정보 뿌리기 
//    @GetMapping("/getMyInfo/userId={userId}")
//    public MyPageUserDTO getMyInfo() {
//    	String userId = "M000006"; // Set the desired hardcoded value for id
//        return myPageService.getMyInfo(userId);
//    }
    
    // 정보 출력
    @PostMapping("/getMyInfo")
    public MyPageUserDTO getMyInfo(@RequestBody Map<String, String> tokenValue) {
    	String token = tokenValue.get("tokenValue");
        JSONObject userInfo = getUserInfo(token);
        String id = userInfo.getString("id");
        return myPageService.getMyInfo(id);
    }
    
    // 정보 수정
    @PostMapping("/updateMyInfo")
    public void updateMyInfo(@RequestBody Map<String, String> tokenValue) {
    	String token = tokenValue.get("tokenValue");
        JSONObject userInfo = getUserInfo(token);
        String id = userInfo.getString("id");
        myPageService.updateMyInfo(id);
    }
    
    //회원 탈퇴 
    @PostMapping("/deleteMyInfo")
    public void deleteMyInfo(@RequestBody Map<String, String> tokenValue){
    	String token = tokenValue.get("tokenValue");
        JSONObject userInfo = getUserInfo(token);
        String id = userInfo.getString("id");
    	myPageService.deleteMyInfo(id);
    }
    
    
}