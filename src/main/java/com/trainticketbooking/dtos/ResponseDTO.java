package com.trainticketbooking.dtos;

import lombok.Data;

@Data
public class ResponseDTO {
    private Boolean isReservationMade;
    private LayoutDetailDTO[] layoutDetails;
}
