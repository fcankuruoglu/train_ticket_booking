package com.trainticketbooking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestDTO {
    @JsonProperty("Tren")
    private TrainDTO train;
    @JsonProperty("RezervasyonYapilacakKisiSayisi")
    private Integer numberOfPassenger;
    @JsonProperty("KisilerFarkliVagonlaraYerlestirilebilir")
    private Boolean canBePlaceInDifferentCarriages;
}
