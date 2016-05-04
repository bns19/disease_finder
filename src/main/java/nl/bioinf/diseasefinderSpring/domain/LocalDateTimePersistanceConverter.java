package nl.bioinf.diseasefinderSpring.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Converts the SQL Date format (used in the MySQL backend) to the Java 8
 * LocalDate format (used in the JPA entities) and back.
 */
@Converter(autoApply = true)
public class LocalDateTimePersistanceConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        return Timestamp.valueOf(entityValue);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
        return databaseValue.toLocalDateTime();
    }
}
