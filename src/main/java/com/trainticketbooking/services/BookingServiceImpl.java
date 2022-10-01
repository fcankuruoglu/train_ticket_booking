package com.trainticketbooking.services;

import ch.qos.logback.core.Layout;
import com.trainticketbooking.dtos.CarriageDTO;
import com.trainticketbooking.dtos.LayoutDetailDTO;
import com.trainticketbooking.dtos.RequestDTO;
import com.trainticketbooking.dtos.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService{
    private final float MAX_OCCUPANCY_RATIO =(float) 0.7;
    @Override
    public ResponseDTO booking(RequestDTO request) {
        ResponseDTO response = new ResponseDTO();
        ArrayList<CarriageDTO> availableCarriages =  findUnder70Carriages(request.getTrain().getCarriages());

        if (availableCarriages.size() == 0 || !checkCapacity(availableCarriages, request.getCanBePlaceInDifferentCarriages() ,request.getNumberOfPassenger())) {
            response.setLayoutDetails(null);
            response.setCanBeBooked(false);
            return response;
        }
        if (!request.getCanBePlaceInDifferentCarriages()){
            response.setLayoutDetails(placedPassengersOnSameCarriage(availableCarriages, request.getNumberOfPassenger()));
        }else {
            response.setLayoutDetails(placedPassengers(availableCarriages, request.getNumberOfPassenger()));
        }
        response.setCanBeBooked(true);
        return response;
    }
    private ArrayList<CarriageDTO> findUnder70Carriages(CarriageDTO[] carriages){
        ArrayList<CarriageDTO> under70Carriages= new ArrayList<>();
        for (int i = 0; i < carriages.length; i++){
            float occupancyRatio = (float) carriages[i].getOccupiedSeat() / carriages[i].getCapacity();
            if (occupancyRatio < MAX_OCCUPANCY_RATIO){
                under70Carriages.add(carriages[i]);
            }
        }
        return under70Carriages;
    }
    private boolean checkCapacity(ArrayList<CarriageDTO> carriages, boolean canBePlaceInDifferentCarriages, int numberOfPassenger){
        int capacity = 0;
        if (canBePlaceInDifferentCarriages){
            for (CarriageDTO carriage :
                    carriages) {
                int availableSeats = ((int)(carriage.getCapacity() * MAX_OCCUPANCY_RATIO) -  carriage.getOccupiedSeat());
                if (capacity < availableSeats){
                    capacity = availableSeats;
                }
            }
        }else {
            for (CarriageDTO carriage :
                    carriages) {
                capacity = capacity + ((int)(carriage.getCapacity() * MAX_OCCUPANCY_RATIO) -  carriage.getOccupiedSeat());
            }
        }
        if (capacity < numberOfPassenger){
            return false;
        }
        return true;
    }
    private List<LayoutDetailDTO> placedPassengersOnSameCarriage(ArrayList<CarriageDTO> availableCarriages, int numberOfPassenger){
        ResponseDTO response = new ResponseDTO();
        Random random = new Random();
        ArrayList<LayoutDetailDTO> layoutDetails = new ArrayList<>();

        while(true){
            int randomIndex = random.nextInt(availableCarriages.size());
            float newOccupancyRatio =(float)(availableCarriages.get(randomIndex).getOccupiedSeat() + numberOfPassenger) / availableCarriages.get(randomIndex).getCapacity();
            if(newOccupancyRatio < 0.7) {
                LayoutDetailDTO tmp = new LayoutDetailDTO();
                tmp.setCarriageName(availableCarriages.get(randomIndex).getName());
                tmp.setNumberOfPassenger(numberOfPassenger);
                layoutDetails.add(tmp);
                break;
            }
        }

        return layoutDetails;
    }
    private ArrayList<LayoutDetailDTO> placedPassengers(ArrayList<CarriageDTO> availableCarriages, int numberOfPassenger){
        Random random = new Random();
        ArrayList<LayoutDetailDTO> layoutDetails = new ArrayList<>();

        while(numberOfPassenger != 0){
            LayoutDetailDTO tmp = new LayoutDetailDTO();
            int randomPassengers = random.nextInt(numberOfPassenger + 1 - 1 ) + 1;
            int randomIndex = random.nextInt(availableCarriages.size());
            float newOccupancyRatio =(float)(availableCarriages.get(randomIndex).getOccupiedSeat() + randomPassengers) / availableCarriages.get(randomIndex).getCapacity();
            System.out.println("ratio: " +newOccupancyRatio);
            System.out.println("randomPassengers: " +randomPassengers);

            tmp.setNumberOfPassenger(randomPassengers);
            tmp.setCarriageName(availableCarriages.get(randomIndex).getName());
            if(newOccupancyRatio < 0.7) {
                if (layoutDetails.size() == 0) {
                    layoutDetails.add(tmp);
                    availableCarriages.get(randomIndex).setOccupiedSeat(availableCarriages.get(randomIndex).getOccupiedSeat() + randomPassengers);
                }else {
                    boolean stateCheck = false;
                    for(int i=0; i<layoutDetails.size();i++){
                        if(tmp.getCarriageName() == layoutDetails.get(i).getCarriageName()){
                            layoutDetails.get(i).setNumberOfPassenger(layoutDetails.get(i).getNumberOfPassenger() + randomPassengers);
                            availableCarriages.get(randomIndex).setOccupiedSeat(availableCarriages.get(randomIndex).getOccupiedSeat() + randomPassengers);
                            stateCheck = true;
                        }
                    }
                    if (stateCheck == false) {
                        layoutDetails.add(tmp);
                        availableCarriages.get(randomIndex).setOccupiedSeat(availableCarriages.get(randomIndex).getOccupiedSeat() + randomPassengers);
                    };
                }
                numberOfPassenger = numberOfPassenger - randomPassengers;
            }
            }

        sortLayoutDetail(layoutDetails);
        return layoutDetails;
    }
    private void sortLayoutDetail(ArrayList<LayoutDetailDTO> list){
        if (list.size() > 0) {
            Collections.sort(list, new Comparator<LayoutDetailDTO>() {
                @Override
                public int compare(final LayoutDetailDTO object1, final LayoutDetailDTO object2) {
                    return object1.getCarriageName().compareTo(object2.getCarriageName());
                }
            });
        }
    }
}
