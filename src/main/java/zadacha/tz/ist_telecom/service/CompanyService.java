package zadacha.tz.ist_telecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zadacha.tz.ist_telecom.dto.ApiResponse;
import zadacha.tz.ist_telecom.dto.CompanyDto;
import zadacha.tz.ist_telecom.entity.Address;
import zadacha.tz.ist_telecom.entity.Company;
import zadacha.tz.ist_telecom.repository.AddressRepository;
import zadacha.tz.ist_telecom.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;


    //Barcha companiyalarni ko'rish
    public ApiResponse getAll() {
        List<Company> all = companyRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("Companiyalar mavjud emas",false,all);
        }
        return new ApiResponse("Companiyalar listi",true,all);
    }

// Ma'lum bir id ga tegishli companiyani ko'rish
    public ApiResponse getOneId(Integer id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent()) {
            return new ApiResponse("Bunday id li companiya mavjud emas",false);
        }
        return new ApiResponse(id+" id li Companiya",true,companyOptional.get());
    }


    //COMPANIYA QO'SHISH
    public ApiResponse add(CompanyDto companyDto) {

        if (companyRepository.existsByCompanyName(companyDto.getCompanyName())) {
            return new ApiResponse("Bunday nomli Companiya mavjud",false,
                    companyRepository.existsByCompanyName(companyDto.getCompanyName()));
        }

        if (companyRepository.existsByEmail(companyDto.getEmail())){
            return new ApiResponse("Bunday email li  Companiya mavjud",false);
        }
        Company company=new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setEmail(companyDto.getEmail());
        company.setDirectorFullName(companyDto.getDirectorFullName());
        company.setWebsite(companyDto.getWebsite());
        company.setPhoneNumber(companyDto.getPhoneNumber());
        Address address=new Address();
        address.setCity(companyDto.getCity());
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        addressRepository.save(address);

        company.setAddress(address);
        Company save = companyRepository.save(company);
        return new ApiResponse("Companiya qo'shildi",true,save);
    }


    public ApiResponse edit(Integer id, CompanyDto companyDto) {

        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent()) {
            return new ApiResponse("Bunday id li companiya mavjud emas",false);
        }

        //Agar shunday id li companiya mavjud bo'lsa keyn o'zgartirishga ruxsat beradi
        Company company = companyOptional.get();
        company.setCompanyName(companyDto.getCompanyName());
        company.setEmail(companyDto.getEmail());
        company.setDirectorFullName(companyDto.getDirectorFullName());
        company.setWebsite(companyDto.getWebsite());
        company.setPhoneNumber(companyDto.getPhoneNumber());


        // Mavjud companiyning adresini o'zgartirish
        Address address = companyOptional.get().getAddress();
        address.setCity(companyDto.getCity());
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());

        addressRepository.save(address);

        company.setAddress(address);
        Company save = companyRepository.save(company);
        return new ApiResponse("Companiya ma'lumotlari o'zgartirildi",true,save);
    }


    // Companiyani o'chirish
    public ApiResponse delete(Integer id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        // Shunday id li companiya bor yoki yo'qligini tekshirish
        if (!companyOptional.isPresent()) {
            return new ApiResponse("Bunday id li companiya mavjud emas",false);
        }
        companyRepository.deleteById(id);
        return new ApiResponse("Companiya o'chirildi",true);
    }
}
