package com.maks.assetaccounting.service;

import com.maks.assetaccounting.converter.CompanyConverter;
import com.maks.assetaccounting.dto.AssetDto;
import com.maks.assetaccounting.dto.CompanyDto;
import com.maks.assetaccounting.entity.Company;
import com.maks.assetaccounting.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.maks.assetaccounting.util.ValidationUtil.assureIdConsistent;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    private final CompanyConverter companyConverter;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyConverter companyConverter) {
        this.companyRepository = companyRepository;
        this.companyConverter = companyConverter;
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        Company company = companyRepository.save(companyConverter.convertToEntity(companyDto));
        return companyConverter.convertToDto(company);
    }

    @Override
    public CompanyDto get(Long id) {
        return companyConverter.convertToDto(companyRepository.findById(id).orElse(null));
    }

    @Override
    public CompanyDto update(CompanyDto companyDto, Long id) {
        assureIdConsistent(companyDto, id);
        return create(companyDto);
    }

    @Override
    public CompanyDto delete(Long id) {
        CompanyDto companyDto = get(id);
        companyRepository.deleteById(id);
        return companyDto;
    }

    @Override
    public List<CompanyDto> getAll() {
        return companyConverter.convertListToDto(companyRepository.findAll());
    }

    @Override
    public List<CompanyDto> findCompaniesWithAssetsInAscendingOrder() {
        List<CompanyDto> companyDtos = getAll();
        companyDtos.forEach(companyDto -> companyDto.getAssetDtos().sort(Comparator.comparing(AssetDto::getCost)));
        return companyDtos;
    }
}
