package com.techie.jobapp.company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompany(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added successfully", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id){
        Company company = companyService.findCompany(id);
        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){

        Boolean deleteResponse  = companyService.deleteCompany(id);
        if (deleteResponse) {
            return new ResponseEntity<>("Removed", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid Id/Id not present", HttpStatus.NOT_FOUND);
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<String>  UpdateCompany(@PathVariable Long id, @RequestBody Company updatedCompany){
        boolean updateResponse = companyService.updateCompany(id, updatedCompany);
        if(updateResponse){
            return  new ResponseEntity<>("Job Updated Successfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
