import React from "react";

import TripScheduleAddStayItem from "./TripScheduleAddStayItem";

import items from "../modal-data/TripScheduleAddStayData";

import styleModalStay from "./TripScheduleAddStay.module.css";

const TripScheduleAddStay = () => {
  return (
    <div className={styleModalStay["trip-schedule-add-stay"]}>
      <div className={styleModalStay["trip-schedule-add-stay-title"]}>
        DAY1 추천 숙박(DAY1, 2, 3 가져오기 🎵)
      </div>
      <div className={styleModalStay["trip-schedule-add-stay-item"]}>
        {items.map((data) => (
          <TripScheduleAddStayItem
            key={data.id}
            stayId={data.id}
            stayImg={data.stayImg}
            stayName={data.stayName}
            stayLocation={data.stayLocation}
            stayType={data.stayType}
          />
        ))}
      </div>
    </div>
  );
};

export default TripScheduleAddStay;
