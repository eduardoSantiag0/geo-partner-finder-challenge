package com.eduardoSantiag0.ze_code.application.port.out;

import com.eduardoSantiag0.ze_code.infra.entities.PartnerEntity;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PartnerRepositoryPort {
  void save (PartnerEntity entity);
  Optional<PartnerEntity> findById(Long id);
  Optional<PartnerEntity> findWithin(double lat, double lon);

    boolean existsById(Long id);
    boolean existsByDocument(String document);
}
