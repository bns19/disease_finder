/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon on 1-5-16.
 */
package nl.bioinf.diseasefinderSpring.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**

 * Converts the SQL Date format (used in the MySQL backend) to the Java 8
 * LocalDate format (used in the JPA entities) and back.
 */
@Converter(autoApply = true)
public class LocalDatePersistanceConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        return Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
        return databaseValue.toLocalDate();
    }
}
