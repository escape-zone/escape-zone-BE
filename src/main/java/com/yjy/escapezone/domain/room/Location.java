package com.yjy.escapezone.domain.room;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum Location {
    NATIONWIDE,
    SEOUL,
    GYEONGGI,
    INCHEON,
    CHUNGCHEONG,
    GYEONGSANG,
    JEOLLA,
    GANGWON,
    JEJU,
    GANGNAM,
    HONGDAE,
    SINCHON,
    KONKUK,
    DAEHANGNO,
    OTHER;

    @Converter
    static class LocationConverter implements AttributeConverter<Location, String> {

        @Override
        public String convertToDatabaseColumn(Location attribute) {
            return attribute.name(); // 열거형 상수의 이름을 문자열로 변환하여 저장
        }

        @Override
        public Location convertToEntityAttribute(String dbData) {
            return Location.valueOf(dbData); // 문자열을 열거형 상수로 변환
        }
    }
}
