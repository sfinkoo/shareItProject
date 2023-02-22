package ru.practicum.shareit.booking;

import lombok.Data;

import java.time.LocalDate;

/**
 * TODO Sprint add-bookings.
 */
@Data
public class Booking {

    LocalDate fromDate;

    LocalDate toDate;

    boolean isConfirmedByOwner;
}

