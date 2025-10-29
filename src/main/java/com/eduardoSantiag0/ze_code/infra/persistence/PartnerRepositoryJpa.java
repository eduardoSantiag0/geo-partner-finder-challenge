package com.eduardoSantiag0.ze_code.infra.persistence;

import com.eduardoSantiag0.ze_code.infra.entities.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PartnerRepositoryJpa extends JpaRepository<PartnerEntity, Long> {
  @Query(value = """
      WITH q AS (
        SELECT ST_SetSRID(ST_MakePoint(:lat, :lon), 4326) AS pt
      )
      SELECT p.*
      FROM partners p, q
      WHERE ST_DWithin(p.coverage_area::geography, q.pt::geography, 0)  
      ORDER BY ST_Distance(p.address::geography, q.pt::geography)       
      LIMIT 1
        """, nativeQuery = true)
  Optional <PartnerEntity> findWithin(@Param("lat") double lat, @Param("lon") double lon);

}
