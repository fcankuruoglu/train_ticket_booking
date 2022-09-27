package com.trainticketbooking.dtos;

import lombok.Data;

@Data
public class TrainDTO {
    private String name;
    private CarriageDTO[] carriages;
}

