import React, { useState } from "react";
import { Link } from "react-router-dom";

import styles from "./MyTripStory.module.css";

const MyStory = (props) => {
    // 추천순, 최신순 선택
    const [isSortedByLike, setIsSortedByLike] = useState(true);
    // 선택한 정렬에 따라 데이터 전환
    const data = isSortedByLike ? props.data : props.data;

    const sortClickHandler = (isLikeSort) => {
        isLikeSort ? setIsSortedByLike(true) : setIsSortedByLike(false);
    };

    // DDay 계산
    // const today = new Date();
    // const startDay = new Date(data.tourStartDay);
    // const timeDiff = startDay.getTime() - today.getTime();
    // const dDay = Math.ceil(timeDiff / (1000 * 60 * 60 * 24)) * -1;

    return (
        <>
            <div className={styles["review-filter"]}>
                <button
                    className={`${styles["review-filter-btn"]} ${
                        isSortedByLike && styles["review-filter-selected"]
                    }`}
                    onClick={() => sortClickHandler(true)}
                >
                    추천순
                </button>
                <button
                    className={`${styles["review-filter-btn"]} ${
                        !isSortedByLike && styles["review-filter-selected"]
                    }`}
                    onClick={() => sortClickHandler(false)}
                >
                    최신순
                </button>
            </div>

            {data.map((item) => (
                <div>
                    <div className={styles["myschedule-mypage-item"]}>
                        {/* 도시 사진 */}
                        <div className={styles["myschedule-mypage-photo"]}>
                            {/* <div className={styles["myschedule-d-day-text"]}>
                    <span>
                        D{dDay < 0 ? dDay : dDay === 0 ? "-day" : `+${dDay}`}
                    </span>
                </div> */}
                            <div className={styles["myschedule-list-image"]}>
                                <img src={item.img} alt="selectedCityImg" />
                            </div>
                        </div>

                        {/* 도시 이름 */}
                        <div className={styles["city-wrap"]}>
                            <p className={styles["list-content-fullcityname"]}>
                                {item.fullCityName} 여행
                            </p>
                            <p className={styles["list-content-cityname"]}>
                                {item.city}
                            </p>
                        </div>

                        {/* 기타 정보 */}
                        <ul className={styles["schedule-info-list"]}>
                            <li className={styles["schedule-info-item"]}>
                                <span className={styles["schedule-info-title"]}>
                                    선택장소
                                </span>
                                <span className={styles["schedule-info-value"]}>
                                    {item.selectedLocation}
                                </span>
                            </li>

                            <li className={styles["schedule-info-item"]}>
                                <span className={styles["schedule-info-title"]}>
                                    여행일자
                                </span>
                                <span className={styles["schedule-info-value"]}>
                                    {item.tourStartDay} - {item.tourEndDay}
                                </span>
                            </li>

                            <li className={styles["schedule-info-item"]}>
                                <span className={styles["schedule-info-title"]}>
                                    최종수정
                                </span>
                                <span className={styles["schedule-info-value"]}>
                                    {item.lastUpdateDay}
                                </span>
                            </li>
                        </ul>

                        <div className={styles["schedule-info-button"]}>
                            <Link to="">
                                <button>보러가기</button>
                            </Link>
                            <Link to="">
                                <button>수정</button>
                            </Link>
                            <button onClick="">삭제</button>
                        </div>
                    </div>
                </div>
            ))}
        </>
    );
};

export default MyStory;
