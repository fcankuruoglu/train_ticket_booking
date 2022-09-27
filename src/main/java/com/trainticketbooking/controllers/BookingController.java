package com.trainticketbooking.controllers;

import com.trainticketbooking.dtos.RequestDTO;
import com.trainticketbooking.dtos.ResponseDTO;
import com.trainticketbooking.services.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookTrainTicket")
    ResponseDTO bookTrainTicket(@RequestBody RequestDTO request){
        return bookingService.booking(request);
    }
}
