package app.bola.flywell.search;

import app.bola.flywell.dto.response.FlightInstanceResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SearchCatalog implements Search {


    @Override
    public Map<LocalDate, List<FlightInstanceResponse>> queryFlightByDate(LocalDate date) {
        return null;
    }
}
