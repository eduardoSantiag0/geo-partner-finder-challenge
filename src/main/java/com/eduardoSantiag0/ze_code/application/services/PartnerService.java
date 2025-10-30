package com.eduardoSantiag0.ze_code.application.services;

import com.eduardoSantiag0.ze_code.application.errors.DuplicateIdException;
import com.eduardoSantiag0.ze_code.application.errors.InvalidCredentialsException;
import com.eduardoSantiag0.ze_code.application.errors.NoPartnerCloseEnoughException;
import com.eduardoSantiag0.ze_code.application.errors.PartnerNotFoundException;
import com.eduardoSantiag0.ze_code.application.services.mappers.PartnerMapper;
import com.eduardoSantiag0.ze_code.application.port.in.PartnerUseCase;
import com.eduardoSantiag0.ze_code.application.port.out.PartnerRepositoryPort;
import com.eduardoSantiag0.ze_code.infra.dtos.CreatePartnerDTO;
import com.eduardoSantiag0.ze_code.infra.entities.PartnerEntity;
import com.eduardoSantiag0.ze_code.infra.dtos.PartnerResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PartnerService implements PartnerUseCase {

    private final PartnerRepositoryPort repository;

    public PartnerService(PartnerRepositoryPort repository) {
        this.repository = repository;
    }

    private void validateDTO(CreatePartnerDTO dto) {
        if (dto.tradingName() == null || dto.tradingName().isBlank()) {
            throw new InvalidCredentialsException("Trading name cannot be blank");
        }
        if (dto.document() == null) {
            throw new InvalidCredentialsException("Document must not be null");
        }
        if (dto.address() == null) {
            throw new InvalidCredentialsException("Address coordinates are required");
        }

        if (repository.existsById(dto.id())) {
            throw new DuplicateIdException("Id already registered");
        }
        if (repository.existsByDocument(dto.document())) {
            throw new DuplicateIdException("Document already registered");
        }

    }

    @Override
    public String createPartner(CreatePartnerDTO dto) {
        this.validateDTO(dto);

        var entity = PartnerFactory.createEntity(dto);

        repository.save(entity);

        return "Created";

    }

    @Override
    public PartnerResponseDTO findById(Long id) {

        PartnerEntity entity = repository.findById(id)
                .orElseThrow(() -> new PartnerNotFoundException("ID not found" + id));

        return PartnerMapper.createResponseDTO(entity);

    }


    @Override
    public PartnerResponseDTO findNearest(double lat, double lon) {

        var entity = repository.findWithin(lat, lon)
                .orElseThrow(() -> new NoPartnerCloseEnoughException("Not found"));

        return PartnerMapper.createResponseDTO(entity);

    }


}
