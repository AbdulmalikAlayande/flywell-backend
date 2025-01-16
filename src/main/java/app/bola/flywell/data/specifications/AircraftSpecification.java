package app.bola.flywell.data.specifications;

import app.bola.flywell.data.model.aircraft.Aircraft;
import org.springframework.data.jpa.domain.Specification;

public class AircraftSpecification {

    public static Specification<Aircraft> hasModel(String model) {
        return (root, query, criteriaBuilder) -> {
            if (model == null || model.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filter
            }
            return criteriaBuilder.equal(root.get("model"), model);
        };
    }

    public static Specification<Aircraft> hasManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            if (manufacturer == null || manufacturer.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filter
            }
            return criteriaBuilder.equal(root.get("manufacturer"), manufacturer);
        };
    }

    public static Specification<Aircraft> hasStatus(String status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null || status.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filter
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<Aircraft> isAvailable() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("available"));
    }

    public static Specification<Aircraft> hasLocation(String locationCode) {
        return (root, query, criteriaBuilder) -> {
            if (locationCode == null || locationCode.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filter
            }
            return criteriaBuilder.equal(root.get("locationCode"), locationCode);
        };
    }

    public static Specification<Aircraft> hasCapacityGreaterThan(Integer capacity) {
        return (root, query, criteriaBuilder) -> {
            if (capacity == null || capacity <= 0) {
                return criteriaBuilder.conjunction(); // No filter
            }
            return criteriaBuilder.greaterThan(root.get("capacity"), capacity);
        };
    }
}
