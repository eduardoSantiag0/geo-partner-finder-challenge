package com.eduardoSantiag0.ze_code.adapter.out;

import com.eduardoSantiag0.ze_code.application.port.out.PartnerRepositoryPort;
import com.eduardoSantiag0.ze_code.infra.entities.PartnerEntity;
import com.eduardoSantiag0.ze_code.infra.persistence.PartnerRepositoryJpa;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PartnerRepositoryAdapter implements PartnerRepositoryPort {

    private final PartnerRepositoryJpa jpa;

    public PartnerRepositoryAdapter(PartnerRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(PartnerEntity entity) {
        jpa.save(entity);
    }

    @Override
    public Optional<PartnerEntity> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public Optional<PartnerEntity> findWithin(double lat, double lon) {
        return jpa.findWithin(lat, lon);
    }

    @Override
    public boolean existsById(Long id) {
        return jpa.existsById(id);
    }

    @Override
    public boolean existsByDocument(String document) {
        return jpa.existsByDocument(document);
    }
}
