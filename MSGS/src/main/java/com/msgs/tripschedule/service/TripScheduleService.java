package com.msgs.tripschedule.service;

import com.msgs.msgs.dto.PlaceInfoDTO;
import java.util.List;

public interface TripScheduleService {
    List<PlaceInfoDTO> getDormList(int areaCode, List<Integer> sigunguCode);

}
