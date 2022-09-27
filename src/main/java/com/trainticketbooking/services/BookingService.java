package com.trainticketbooking.services;

import com.trainticketbooking.dtos.RequestDTO;
import com.trainticketbooking.dtos.ResponseDTO;

public interface BookingService {

    ResponseDTO booking(RequestDTO request);
}
