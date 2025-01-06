-- Creation of a sequence table for flightNumber in FlightInstance model.
CREATE TABLE flight_number_sequence (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        next_val INT NOT NULL
);

INSERT INTO flight_number_sequence (next_val) VALUES (1000); -- Start with 1
-- End of sequence table creation.
