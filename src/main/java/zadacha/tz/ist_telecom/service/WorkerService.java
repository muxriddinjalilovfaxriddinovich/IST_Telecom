package zadacha.tz.ist_telecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zadacha.tz.ist_telecom.dto.ApiResponse;
import zadacha.tz.ist_telecom.dto.WorkerDto;
import zadacha.tz.ist_telecom.entity.Address;
import zadacha.tz.ist_telecom.entity.Company;
import zadacha.tz.ist_telecom.entity.Worker;
import zadacha.tz.ist_telecom.repository.AddressRepository;
import zadacha.tz.ist_telecom.repository.CompanyRepository;
import zadacha.tz.ist_telecom.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;



    //Barcha ishchilar ro'yxatini ko'rish
    public ApiResponse getAll() {
        List<Worker> all = workerRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("Ishchilar listi bo'sh",false);
        }
        return new ApiResponse("Ishchilar listi ",true,all);
    }


    //Bitta ishchini ma'lumotlarini ko'rish
    public ApiResponse getOneId(Integer id) {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (!workerOptional.isPresent()) {
            return new ApiResponse("Bunday id li ishchi mavjud emas",false);
        }
        return new ApiResponse(id+" li ishchi",true,workerOptional.get());
    }

    // Ishchi qo'shish
    public ApiResponse add(WorkerDto workerDto) {
        Optional<Company> companyOptional = companyRepository.findById(workerDto.getCompanyId());
        if (!companyOptional.isPresent()) {
            return new ApiResponse("Companiya mavjud emas",false);
        }


        // Passport malumotlari takrorlanmasligini tekshirish
        if (workerRepository.existsByPassportSeriesAndPassportNumber(workerDto.getPassportSeries(), workerDto.getPassportNumber())) {
            return new ApiResponse("Bunday Passport ma'lumotli ishchi mavjud",false);
        }

        //Telifon raqam unique ligi tekshirilgan
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber())){
            return new ApiResponse("Bunday telifon raqam mavjud",false);
        }

        Worker worker=new Worker();
        worker.setCompany(companyOptional.get());
        worker.setFirstName(workerDto.getFirstName());
        worker.setLastName(workerDto.getLastName());
        worker.setMiddleName(workerDto.getMiddleName());
        worker.setPosition(workerDto.getPosition());
        worker.setPassportSeries(workerDto.getPassportSeries());
        worker.setPassportNumber(workerDto.getPassportNumber());
        worker.setPhoneNumber(workerDto.getPhoneNumber());

        Address address=new Address();
        address.setCity(workerDto.getCity());
        address.setHomeNumber(workerDto.getHomeNumber());
        address.setStreet(workerDto.getStreet());
        addressRepository.save(address);
        worker.setAddress(address);
        Worker save = workerRepository.save(worker);
        return new ApiResponse("Ishchi qo'shildi",true,save);
    }

    public ApiResponse edit(Integer id, WorkerDto workerDto) {

        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (!workerOptional.isPresent()) {
            return new ApiResponse("Bunday Id li companiya mavjud emas",false);
        }

        Worker worker = workerOptional.get();
        worker.setCompany(workerOptional.get().getCompany());
        worker.setFirstName(workerDto.getFirstName());
        worker.setLastName(workerDto.getLastName());
        worker.setMiddleName(workerDto.getMiddleName());
        worker.setPosition(workerDto.getPosition());
        worker.setPassportSeries(workerDto.getPassportSeries());
        worker.setPassportNumber(workerDto.getPassportNumber());
        worker.setPhoneNumber(workerDto.getPhoneNumber());

        Address address = workerOptional.get().getAddress();

        address.setCity(workerDto.getCity());
        address.setHomeNumber(workerDto.getHomeNumber());
        address.setStreet(workerDto.getStreet());
        addressRepository.save(address);
        worker.setAddress(address);
        Worker save = workerRepository.save(worker);
        return new ApiResponse("O'zgartirildi",false,save);

    }


    public ApiResponse delete(Integer id) {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (!workerOptional.isPresent()) {
            return new ApiResponse("Bunday id li ishchi mavjud emas",false);
        }

        workerRepository.deleteById(id);
        return new ApiResponse("O'chirildi", true);
    }
}
