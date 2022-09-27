package com.trainticketbooking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrainDTO {
    @JsonProperty("Ad")
    private String name;
    @JsonProperty("Vagonlar")
    private CarriageDTO[] carriages;
}

