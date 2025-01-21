package app.bola.flywell.services.flightformservice;

import app.bola.flywell.dto.request.FlightFormRequest;
import app.bola.flywell.dto.response.FlightFormResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightFormServiceImplementation implements FlightFormService {


	@Override
	public FlightFormResponse createNew(FlightFormRequest request) {
		return null;
	}

	@Override
	public FlightFormResponse findByPublicId(String publicId) {
		return null;
	}

	@Override
	public boolean existsByPublicId(String publicId) {
		return false;
	}

	@Override
	public Collection<FlightFormResponse> findAll() {
		return List.of();
	}

	@Override
	public void removeAll() {

	}

	@Override
	public Collection<FlightFormResponse> findAll(Pageable pageable) {
		return List.of();
	}
}