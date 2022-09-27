package com.trainticketbooking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LayoutDetailDTO {
    @JsonProperty("VagonAdi")
    private String carriageName;
    @JsonProperty("KisiSayisi")
    private Integer numberOfPeople;
}
