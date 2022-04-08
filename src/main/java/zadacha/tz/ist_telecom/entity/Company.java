package zadacha.tz.ist_telecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;

    private String DirectorFullName;

    @OneToOne(cascade = CascadeType.REMOVE)
    private  Address address;
    @Column(unique = true)
    private String email;

    private String website;

    @Column(length = 13)
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY,
            mappedBy = "company")
    private List<Worker> workerList;


}
