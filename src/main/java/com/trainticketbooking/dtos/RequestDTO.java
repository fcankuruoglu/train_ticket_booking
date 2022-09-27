package com.trainticketbooking.dtos;

import lombok.Data;

@Data
public class RequestDTO {
    private TrainDTO train;
    private Integer numberOfPeople;
    private Boolean canBePlacedInDifferentCarriage;
}
