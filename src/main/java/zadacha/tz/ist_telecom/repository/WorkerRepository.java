package zadacha.tz.ist_telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zadacha.tz.ist_telecom.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    boolean existsByPassportSeriesAndPassportNumber(String passportSeries , String passportNumber);
    boolean existsByPhoneNumber(String phoneNumber);

}
