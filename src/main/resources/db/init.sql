-- Creation of a sequence table for flightNumber in FlightInstance model.
CREATE TABLE IF NOT EXISTS flight_number_sequence (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        next_val INT NOT NULL
);

INSERT INTO flight_number_sequence (next_val) VALUES (1000); -- Start with 1
-- End of sequence table creation.

-- Creation of a sequence table for hangarId in Aircraft model.
CREATE TABLE IF NOT EXISTS hangar_id_sequence (
                                    location_code VARCHAR(10) NOT NULL PRIMARY KEY,
                                    current_sequence INT NOT NULL
);
-- End of sequence table creation.