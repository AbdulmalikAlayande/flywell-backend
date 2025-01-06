SELECT CONCAT('ALTER TABLE ', TABLE_NAME, ' ADD COLUMN version BIGINT DEFAULT 0;') AS sql_script
FROM information_schema.tables
WHERE TABLE_SCHEMA = 'flywell_db'
  AND TABLE_NAME IN ('flight_instance', 'flight', 'airport'); -- Add other entity tables

