package zadacha.tz.ist_telecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class WorkerDto {

    private String passportSeries;

    private String passportNumber;

    private String firstName;

    private String lastName;

    private String middleName;

    private String  position;

    private String phoneNumber;

    private String city;

    private String street;

    private String homeNumber;

    private Integer companyId;

}
