package com.maks.assetaccounting.controller;

import com.maks.assetaccounting.dto.CompanyDto;
import com.maks.assetaccounting.service.company.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody final CompanyDto companyDto) {
        log.info("create {}", companyDto);
        return companyService.create(companyDto);
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies() {
        log.info("get all companies");
        return companyService.getAll();
    }

    @GetMapping("{id}")
    public CompanyDto getCompany(@PathVariable("id") final Long id) {
        log.info("get company with id = {}", id);
        return companyService.get(id);
    }
}



