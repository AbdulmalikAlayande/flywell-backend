package app.bola.flywell.generator;

import app.bola.flywell.data.tables.HangarIdSequence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Generates unique hanger IDs based on the location and current date.
 */
@Service
@Transactional
@AllArgsConstructor
public class AircraftHangerIdGenerator {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    @PersistenceContext
    final EntityManager entityManager;
    final Logger logger = LoggerFactory.getLogger(AircraftHangerIdGenerator.class);


    /**
     * Generates a unique hanger ID based on the location and current date.
     *
     * @return A unique hanger ID.
     */
    public String generateHangerId(String locationCode) {
        if (locationCode == null || locationCode.isEmpty()) {
            throw new IllegalArgumentException("Location code cannot be null or empty.");
        }
        String datePart = LocalDateTime.now().format(dateFormatter);
        int sequenceNumber = getNextSequenceNumber(locationCode.toUpperCase());

        // Format: HNG-<LOCATION_CODE>-<DATE>-<SEQUENCE>
        return String.format("HNG-%s-%s-%04d", locationCode.toUpperCase(), datePart, sequenceNumber);
    }

    /**
     * Retrieves the next sequence number for the specified location code.
     *
     * @return the next sequence number.
     * @throws RuntimeException if there is an error fetching or updating the sequence number.
     */
    private int getNextSequenceNumber(String locationCode) {
        try {
            Object query = entityManager.createQuery("""
                            SELECT h.currentSequence FROM HangarIdSequence h WHERE h.locationCode = :locationCode
                            """)
                    .setParameter("locationCode", locationCode)
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE) // Ensure safe updates
                    .getSingleResult();

            Integer currentSequence = (Integer) query;

            if (currentSequence == null) {
                return initializeNewSequence(locationCode);
            }
            int nextSequence = currentSequence + 1;
            entityManager.createQuery(
                            "UPDATE HangarIdSequence h SET h.currentSequence = :nextSequence WHERE h.locationCode = :locationCode")
                    .setParameter("nextSequence", nextSequence)
                    .setParameter("locationCode", locationCode)
                    .executeUpdate();

            return nextSequence;
        } catch (NoResultException e) {
            return initializeNewSequence(locationCode);
        } catch (Exception e) {
            logger.error("Error fetching or updating sequence number", e);
            throw new RuntimeException("Error fetching or updating sequence number", e);
        }
    }

    private int initializeNewSequence(String locationCode) {
        HangarIdSequence newSequence = new HangarIdSequence();
        newSequence.setLocationCode(locationCode);
        newSequence.setCurrentSequence(1);
        entityManager.persist(newSequence);
        return 1;
    }
}