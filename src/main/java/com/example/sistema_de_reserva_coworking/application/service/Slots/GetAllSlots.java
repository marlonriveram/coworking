package com.example.sistema_de_reserva_coworking.application.service.Slots;

import com.example.sistema_de_reserva_coworking.application.dto.slot.SlotResponse;
import com.example.sistema_de_reserva_coworking.domain.model.ReservationSlot;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GetAllSlots {

    public List<SlotResponse> getAllSlots() {
        return Arrays.stream(ReservationSlot.values())
                .map(slot -> new SlotResponse(
                        slot.name(),
                        slot.getStartTime(),
                        slot.getEndTime()
                ))
                .toList();
    }


}
