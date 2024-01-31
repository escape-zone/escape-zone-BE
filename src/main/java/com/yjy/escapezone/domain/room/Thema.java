package com.yjy.escapezone.domain.room;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum Thema {
    HORROR,
    SENTIMENTAL,
    MYSTERY,
    ACTION,
    SF,
    OTHER;

    @Converter
    static class ThemaConverter implements AttributeConverter<Thema, String> {

        @Override
        public String convertToDatabaseColumn(Thema attribute) {
            return attribute.name(); // 열거형 상수의 이름을 문자열로 변환하여 저장
        }

        @Override
        public Thema convertToEntityAttribute(String dbData) {
            return Thema.valueOf(dbData); // 문자열을 열거형 상수로 변환
        }
    }
}
