package zadacha.tz.ist_telecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zadacha.tz.ist_telecom.dto.ApiResponse;
import zadacha.tz.ist_telecom.dto.CompanyDto;
import zadacha.tz.ist_telecom.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PreAuthorize("hasAnyAuthority('ADMIN','COMPANY')")
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse getAll=companyService.getAll();
//        return ResponseEntity.status(getAll.isSuccess()? HttpStatus.OK:HttpStatus.NO_CONTENT).body(getAll);
        return ResponseEntity.ok(getAll);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','COMPANY')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        ApiResponse getOneId=companyService.getOneId(id);
        return ResponseEntity.status(getOneId.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(getOneId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','COMPANY')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody CompanyDto companyDto){
        ApiResponse add=companyService.add(companyDto);

//        return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(add);
        return ResponseEntity.ok(add);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','COMPANY')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id , @RequestBody CompanyDto companyDto){
        ApiResponse edit=companyService.edit(id,companyDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(edit);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','COMPANY')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delet=companyService.delete(id);
        return ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(delet);
    }
}
