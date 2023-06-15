import React, { useState } from 'react';
import StarRating from '../../../components/tripstory/tripstory-write/StarRating';
import UploadPhoto from '../../../components/tripstory/tripstory-write/UploadPhoto';
import UploadStoryBtn from '../../../components/tripstory/tripstory-write/UploadStoryBtn';
import styles from "./SpotModal.module.css";

//tripstory 글작성 모달창 컴포넌트입니다.
const SpotModal = ({onClose, spot, handleSpotContent}) => {
    const [rating, setRating] = useState(0);
    const [content, setContent] = useState(""); // SpotModal 내부에서 입력된 텍스트

    const handleRatingChange = (event) => {
        setRating(0);
        setRating(Number(event.target.value));
    };

    const handleContentChange = (event) => {
        setContent(event.target.value); // 텍스트 내용 업데이트
    };

    const handleUploadStory = () => {
        handleSpotContent(content); // SpotItem 컴포넌트로 텍스트 전달
        onClose(); // SpotModal 닫기
    };

    
    return (
        <>
            <div className={styles["bg"]}></div>   
            <div className={styles["popup"]}>
                <p onClick={ onClose } className={styles["closex"]} >X</p>
                <h2>{spot}</h2>
                <hr/>
                <div className={styles["trip-score-ment2"]}>이 장소의 평점을 입력해주세요.
                    <StarRating rating={rating} handleRatingChange={handleRatingChange} /> {/* 별점 매기기 컴포넌트 */}
                </div>
                <textarea className={styles['tripstory-content']} 
                          placeholder='이 장소에서의 경험과 추억을 공유해주세요.'
                          value={content}
                          onChange={handleContentChange} //텍스트 내용 변경 핸들러
                          ></textarea>
                <hr/>
                <UploadPhoto /> {/* 사진 첨부 버튼 */}
                <UploadStoryBtn onClose={ handleUploadStory } />
            </div>
        </>
    );
};

export default SpotModal;