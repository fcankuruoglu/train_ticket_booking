package com.trainticketbooking.dtos;

import lombok.Data;

@Data
public class CarriageDTO {
    private String name;
    private Integer capacity;
    private Integer occupiedSeat;
}
