package com.trainticketbooking.controllers;

import com.trainticketbooking.dtos.RequestDTO;
import com.trainticketbooking.dtos.ResponseDTO;
import com.trainticketbooking.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/book")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/ticket")
    ResponseEntity<ResponseDTO> bookTrainTicket(@RequestBody RequestDTO request){
        return ResponseEntity.ok().body(bookingService.booking(request));
    }
}
