package zadacha.tz.ist_telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zadacha.tz.ist_telecom.entity.Company;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company , Integer> {

boolean existsByCompanyName(String companyName);

boolean existsByEmail(String email);

}
