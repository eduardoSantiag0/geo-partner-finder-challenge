package com.eduardoSantiag0.ze_code.application.services;

import com.eduardoSantiag0.ze_code.application.services.mappers.GeoMapper;
import com.eduardoSantiag0.ze_code.infra.dtos.CreatePartnerDTO;
import com.eduardoSantiag0.ze_code.infra.entities.PartnerEntity;
import org.springframework.stereotype.Service;

@Service
public class PartnerFactory {

  public static PartnerEntity createEntity(CreatePartnerDTO dto) {
    var convertedCoverageArea = GeoMapper
        .convertCoverageToMultiPoligon(dto.coverageArea());

    var convertedAddress = GeoMapper.convertToPoint(dto.address());

    return new PartnerEntity(dto.id(),
        dto.tradingName(), dto.ownerName(), dto.document(),
        convertedCoverageArea, convertedAddress);
  }
}
