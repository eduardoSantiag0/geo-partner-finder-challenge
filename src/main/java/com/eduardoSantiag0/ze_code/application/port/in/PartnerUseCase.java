package com.eduardoSantiag0.ze_code.application.port.in;

import com.eduardoSantiag0.ze_code.infra.dtos.CreatePartnerDTO;
import com.eduardoSantiag0.ze_code.infra.dtos.PartnerResponseDTO;

public interface PartnerUseCase {
  String createPartner(CreatePartnerDTO dto);
  PartnerResponseDTO findById (Long id);
  PartnerResponseDTO findNearest(double lat, double lon);
}
