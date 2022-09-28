package com.trainticketbooking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {
    @JsonProperty("RezervasyonYapilabilir")
    private Boolean canBeBooked;
    @JsonProperty("YerlesimAyrinti")
    private List<LayoutDetailDTO> layoutDetails;
}
