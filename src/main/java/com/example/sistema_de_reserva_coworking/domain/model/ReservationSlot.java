package com.example.sistema_de_reserva_coworking.domain.model;

public enum ReservationSlot {
    MORNING_1("08:00", "10:00"),
    MORNING_2("10:00", "12:00"),
    AFTERNOON_1("14:00", "16:00"),
    AFTERNOON_2("16:00", "18:00");

    private final String startTime;
    private final String endTime;

 ReservationSlot (String startTime, String endTime) {
     this.startTime = startTime;
     this.endTime = endTime;
 }

    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
}
