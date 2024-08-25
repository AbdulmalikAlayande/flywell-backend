package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.dtos.Request.CreateFlightInstanceRequest;
import app.bola.flywell.dtos.Response.FlightInstanceResponse;
import app.bola.flywell.exceptions.InvalidRequestException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface FlightInstanceService {
	
	FlightInstanceResponse createNewInstance(CreateFlightInstanceRequest flightInstanceRequest) throws InvalidRequestException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
	
	List<FlightInstanceResponse> findAllBy(FlightStatus status);
	
	void removeAll();
}
