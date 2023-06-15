import React from 'react';
import Calendar from './Calendar';
import styles from './TripSchedule2.module.css';


const TripSchedule2 = () => {
  return (
    <div className={styles.tripScheduleAddModal}>
      <div className={styles.content}>
        <div className={styles.tripScheduleAddModalClose}>
            <img
              src="../../../public/images/icon_close.png"
              alt="icon_close"
            />
        </div>
        <div className={styles.titleContainer}>
          <h1 className={styles.title}>여행일정 등록</h1><br/>
          <h2 className={styles.subtitle}>일정에 따른 일기예보, 여행정보를 알려드립니다.</h2>
        </div>
        <Calendar />        
        <br /><br />
      </div>
    </div>
  );
};

export default TripSchedule2;