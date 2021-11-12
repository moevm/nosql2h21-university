package com.example.universitiesandapplicants.model.respose;

import lombok.Data;

@Data
public class EnrolleeStatisticsResponseModel {

    // Подали заявление в ваш ВУЗ
    private Integer numInYourUniversity;
    // Подали заявление в другие ВУЗы
    private Integer numInOtherUniversities;
    // Не подали заявление
    private Integer numWithoutStatement;
    // Подали заявление в ВУЗ: <указанный ВУЗ>
    private Integer numInSpecifiedUniversity = -1;
    // Подали заявление на направление: <указанное направление>
    private Integer numInSpecifiedDirection = -1;
}
