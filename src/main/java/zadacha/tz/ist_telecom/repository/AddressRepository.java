package zadacha.tz.ist_telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zadacha.tz.ist_telecom.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
