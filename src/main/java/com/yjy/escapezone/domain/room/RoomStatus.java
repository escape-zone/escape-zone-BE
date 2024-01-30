package com.yjy.escapezone.domain.room;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum RoomStatus{
    START,
    WAITING,
    DONE;

    @Converter
    static class RoomStatusConverter implements AttributeConverter<RoomStatus, String> {

        @Override
        public String convertToDatabaseColumn(RoomStatus attribute) {
            return attribute.name(); // 열거형 상수의 이름을 문자열로 변환하여 저장
        }

        @Override
        public RoomStatus convertToEntityAttribute(String dbData) {
            return RoomStatus.valueOf(dbData); // 문자열을 열거형 상수로 변환
        }
    }
}
