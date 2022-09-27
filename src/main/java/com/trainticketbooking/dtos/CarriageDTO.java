package com.trainticketbooking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarriageDTO {
    @JsonProperty("Ad")
    private String name;
    @JsonProperty("Kapasite")
    private Integer capacity;
    @JsonProperty("DoluKoltukAdet")
    private Integer occupiedSeat;
}
