package com.techie.jobapp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    void createCompany(Company company);

    Company findCompany(Long id);
    Boolean deleteCompany(Long id);

    Boolean updateCompany(Long id, Company updatedCompany);
}
