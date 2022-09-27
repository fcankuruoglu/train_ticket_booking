package com.trainticketbooking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseDTO {
    @JsonProperty("RezervasyonYapilabilir")
    private Boolean canBeBooked;
    @JsonProperty("YerlesimAyrinti")
    private LayoutDetailDTO[] layoutDetails;
}
