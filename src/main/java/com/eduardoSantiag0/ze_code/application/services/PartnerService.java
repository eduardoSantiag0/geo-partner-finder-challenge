package com.eduardoSantiag0.ze_code.application.services;

import com.eduardoSantiag0.ze_code.infra.dtos.CreatePartnerDTO;
import com.eduardoSantiag0.ze_code.infra.entities.PartnerEntity;
import com.eduardoSantiag0.ze_code.infra.persistence.PartnerRepository;
import com.eduardoSantiag0.ze_code.infra.dtos.PartnerResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

  private final PartnerRepository repository;

  public PartnerService(PartnerRepository repository) {
    this.repository = repository;
  }

  private void validateDTO (CreatePartnerDTO dto) {

  }

  public String createPartner(CreatePartnerDTO dto) {
    this.validateDTO(dto);

    var entity = PartnerFactory.createEntity(dto);

    repository.save(entity);

    return "Created";

  }

  public PartnerResponseDTO findById (Long id) {

    PartnerEntity entity = repository.findById(id).orElseThrow( () -> new RuntimeException("ID not found" + id));

    return PartnerFactory.createResponseDTO(entity);

  }

  public PartnerResponseDTO findNearest(double lat, double lon) {

    var entity = repository.findWithin(lat, lon).orElseThrow( () -> new RuntimeException("Not found"));

    return PartnerFactory.createResponseDTO(entity);

  }



}
