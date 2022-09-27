package com.trainticketbooking.services;

import com.trainticketbooking.dtos.RequestDTO;
import com.trainticketbooking.dtos.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{
    @Override
    public ResponseDTO booking(RequestDTO request) {
        //test
        System.out.println(request.getTrain().getName());
        System.out.println(request.getTrain().getCarriages()[0].getName());
        System.out.println(request.getTrain().getCarriages()[0].getCapacity());
        System.out.println(request.getTrain().getCarriages()[0].getOccupiedSeat());
        ResponseDTO response = new ResponseDTO();
        response.setLayoutDetails(null);
        response.setCanBeBooked(false);
        return response;

    }
}
