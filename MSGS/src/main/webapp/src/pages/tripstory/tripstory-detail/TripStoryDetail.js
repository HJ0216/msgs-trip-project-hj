import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

import styles from "./TripStoryDetail.module.css";
import Loading from "../../../components/common/Loading";
import TripStoryDay from "./TripStoryDay";

const TripStoryDetail = () => {
    // 유저 아이디 가져옴
    const userId = "user01"; // 임시로 지정함

    // 파라미터에서 데이터 가져옴
    const { storyId } = useParams();

    // API 데이터 담을 state
    const [data, setData] = useState(null);

    // 이야기 좋아요 클릭 여부
    const [isLiked, setIsLiked] = useState();

    // back-end에서 API 호출
    useEffect(() => {
        const getData = async () => {
            try {
                const detailResponse = await axios.post(
                    "/tripstory/getStoryDetail",
                    {
                        storyId,
                    }
                );
                setData(detailResponse.data);

                // 좋아요 데이터 가져오기
                const likeResponse = await axios.post(
                    "/tripstory/getStoryLike",
                    {
                        storyId,
                        userId,
                    }
                );
                setIsLiked(likeResponse.data);
            } catch (error) {
                console.log("Error occurred:", error);
            }
        };
        getData();
    }, []);

    // 좋아요 클릭으로 state 변경 시마다 back 연결
    useEffect(() => {
        const likeUpdate = async () => {
            try {
                await axios.post("/tripstory/storyLikeUpdate", {
                    storyId,
                    userId,
                });
            } catch (error) {
                console.log("Error occurred:", error);
            }
        };
        likeUpdate();
    }, [isLiked]);

    // 이미지 엑박 처리
    const [imageError, setImageError] = useState(false);
    const handleImageError = () => {
        setImageError(true);
    };

    return (
        <>
            {!data ? (
                <Loading />
            ) : (
                <div className={styles["width-wrapper"]}>
                    {!imageError && ( // 이미지 엑박일 경우 띄우지 않음
                        <div className={styles["thumbnail-img-wrap"]}>
                            <img
                                className={styles["thumbnail-img"]}
                                src={data.firstimage}
                                alt={data.title}
                                onError={handleImageError}
                            />
                        </div>
                    )}
                    <h1 className={styles["story-title"]}>
                        {data.title}
                        <img
                            className={`${styles["thumbsup-icon"]} ${
                                isLiked && styles["thumbsup-icon-filled"]
                            }`}
                            src={`${process.env.PUBLIC_URL}/images/free-icon-like-126473.png`}
                            onClick={() =>
                                setIsLiked((prevIsLiked) => !prevIsLiked)
                            }
                        />
                    </h1>

                    <span className={styles["story-comment"]}>
                        {data.comment}
                    </span>

                    {/* day별 데이터 map 돌림 */}
                    {data.tripDetailList.map((item, index) => (
                        <TripStoryDay dayData={item} />
                    ))}
                </div>
            )}
        </>
    );
};

export default TripStoryDetail;
