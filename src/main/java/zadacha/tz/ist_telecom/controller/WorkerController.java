package zadacha.tz.ist_telecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zadacha.tz.ist_telecom.dto.ApiResponse;
import zadacha.tz.ist_telecom.dto.WorkerDto;
import zadacha.tz.ist_telecom.service.WorkerService;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;


    @PreAuthorize("hasAnyAuthority('ADMIN','COMPANY')")
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse getAll = workerService.getAll();
        return ResponseEntity.ok(getAll);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','COMPANY')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        ApiResponse getOneId=workerService.getOneId(id);
        return ResponseEntity.ok(getOneId);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody WorkerDto workerDto){
        ApiResponse add=workerService.add(workerDto);
        return ResponseEntity.ok(add);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id , @RequestBody WorkerDto workerDto){
        ApiResponse edit = workerService.edit(id,workerDto);
        return ResponseEntity.ok(edit);
    }


    @PreAuthorize("hasAuthority('COMPANY')")
    @DeleteMapping("/{id}")
    public  HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delete= workerService.delete(id);
        return  ResponseEntity.ok(delete);
    }
}
