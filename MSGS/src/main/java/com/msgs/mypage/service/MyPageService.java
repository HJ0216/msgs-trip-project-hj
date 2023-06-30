package com.msgs.mypage.service;

import com.msgs.msgs.dto.MyPageScheduleDTO;
import com.msgs.mypage.dto.MyPageUserDTO;

import java.util.List;

import com.msgs.msgs.dto.TripScheduleDTO;

public interface MyPageService {

	List<TripScheduleDTO> tripListAll(String id);
  
  
	MyPageUserDTO getMyInfo(String userId);

	void userDelete(String useId);

//	void insertImgPath(String imagePath, String userId);

	List<MyPageScheduleDTO> getScheduleList(String id);

}
