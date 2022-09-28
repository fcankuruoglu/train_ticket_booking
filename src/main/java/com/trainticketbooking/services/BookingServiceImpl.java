package com.trainticketbooking.services;

import com.trainticketbooking.dtos.CarriageDTO;
import com.trainticketbooking.dtos.LayoutDetailDTO;
import com.trainticketbooking.dtos.RequestDTO;
import com.trainticketbooking.dtos.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

@Service
public class BookingServiceImpl implements BookingService{
    @Override
    public ResponseDTO booking(RequestDTO request) {

        ResponseDTO response = new ResponseDTO();

        ArrayList<CarriageDTO> availableCarriages =  findUnder70Carriages(request.getTrain().getCarriages());
        //ArrayList<LayoutDetailDTO> layoutDetails = placedPassengers(availableCarriages, request.getNumberOfPassenger());

        // if there is no available carriage, it returns with false and empty array.
        if (availableCarriages.size() == 0) {
            response.setLayoutDetails(null);
            response.setCanBeBooked(false);
            return response;
        }
        response.setCanBeBooked(true);
        response.setLayoutDetails(placedPassengers(availableCarriages, request.getNumberOfPassenger()));


        return response;
    }
    // Find all carriages with an occupancy ratio less than %70.
    private ArrayList<CarriageDTO> findUnder70Carriages(CarriageDTO[] carriages){
        ArrayList<CarriageDTO> under70Carriages= new ArrayList<CarriageDTO>();
        for (int i = 0; i < carriages.length; i++){
            float occupancyRatio = (float) carriages[i].getOccupiedSeat() / carriages[i].getCapacity();
            if (occupancyRatio < 0.7){
                under70Carriages.add(carriages[i]);
            }
        }
        return under70Carriages;
    }
    // Placed all passengers in available carriages.
    private ArrayList<LayoutDetailDTO> placedPassengers(ArrayList<CarriageDTO> availableCarriages, int numberOfPassenger){
        Random random = new Random();
        ArrayList<LayoutDetailDTO> layoutDetails = new ArrayList<>();

        while(numberOfPassenger != 0){
            LayoutDetailDTO tmp = new LayoutDetailDTO();
            int randomPassengers = random.nextInt(numberOfPassenger + 1 - 1 ) + 1;
            int randomIndex = random.nextInt(availableCarriages.size());
            tmp.setNumberOfPassenger(randomPassengers);
            tmp.setCarriageName(availableCarriages.get(randomIndex).getName());

//            for(LayoutDetailDTO layoutDetail : layoutDetails){
//                if(tmp.getCarriageName() == layoutDetail.getCarriageName()){
//                    layoutDetail.setNumberOfPassenger(layoutDetail.getNumberOfPassenger() + randomPassengers);
//                }
//            }
            // TODO: 28/09/2022 last tmp add list again. fix it.
            for(int i=0; i<layoutDetails.size();i++){
                if(tmp.getCarriageName() == layoutDetails.get(i).getCarriageName()){
                    layoutDetails.get(i).setNumberOfPassenger(layoutDetails.get(i).getNumberOfPassenger() + randomPassengers);
                }
            }

            //layoutDetails.add(tmp);
            if (layoutDetails.size() == 0 || (tmp.getNumberOfPassenger() != null || tmp.getCarriageName() != null)) {layoutDetails.add(tmp);}
            numberOfPassenger = numberOfPassenger - randomPassengers;
        }
        return layoutDetails;
    }
}
