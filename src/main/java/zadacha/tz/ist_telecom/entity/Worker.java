package zadacha.tz.ist_telecom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {
        "passportSeries", "passportNumber" ,"phoneNumber"})})
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Pattern(regexp = "[A-Z]{2}")
    private String passportSeries;

    @Pattern(regexp = "[0-9]{7}")
    private String passportNumber;

    private String firstName;

    private String lastName;

    private String middleName;

    private String  position;

    @Column(unique = true)
//    @Pattern(regexp = "[998]{2}[0-9]{9}")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.REMOVE)
    private  Address address;

    @ManyToOne
    private Company company;
}
