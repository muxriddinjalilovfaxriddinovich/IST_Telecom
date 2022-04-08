package zadacha.tz.ist_telecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {

    private String companyName;

    private String DirectorFullName;

    private String city;

    private String street;

    private String homeNumber;

    private String email;

    private String website;

    private String phoneNumber;
}
