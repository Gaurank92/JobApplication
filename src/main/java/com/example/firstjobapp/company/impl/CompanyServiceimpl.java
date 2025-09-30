package com.example.firstjobapp.company.impl;

import com.example.firstjobapp.company.Company;
import com.example.firstjobapp.company.CompanyRepository;
import com.example.firstjobapp.company.CompanyService;
import com.example.firstjobapp.job.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceimpl implements CompanyService {

    private CompanyRepository companyRepository;
    private JobRepository jobRepository;

    public CompanyServiceimpl(CompanyRepository companyRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company updatedCompany = companyOptional.get();
            updatedCompany.setDescription(company.getDescription());
            updatedCompany.setName(company.getName());
            updatedCompany.setJobs(company.getJobs());
            companyRepository.save(updatedCompany);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            jobRepository.deleteAll(company.getJobs());
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

}
