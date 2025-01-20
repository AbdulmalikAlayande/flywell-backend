package app.bola.flywell.search;

import app.bola.flywell.dto.response.FlightInstanceResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Search {

    Map<LocalDate, List<FlightInstanceResponse>> queryFlightByDate(LocalDate date);
}
