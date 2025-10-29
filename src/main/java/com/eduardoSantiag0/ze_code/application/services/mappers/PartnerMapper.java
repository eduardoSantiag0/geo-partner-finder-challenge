package com.eduardoSantiag0.ze_code.application.services.mappers;

import com.eduardoSantiag0.ze_code.infra.dtos.PartnerResponseDTO;
import com.eduardoSantiag0.ze_code.infra.entities.PartnerEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {
    public static PartnerResponseDTO createResponseDTO (PartnerEntity entity) {

        ObjectMapper mapper = new ObjectMapper();

        GeoJsonWriter writer = new GeoJsonWriter();
        writer.setEncodeCRS(false);


        ObjectNode coverageAreaNode = mapper.createObjectNode();
        ObjectNode addressNode = mapper.createObjectNode();

        try {
            coverageAreaNode.setAll((ObjectNode) mapper.readTree(writer.write(entity.getCoverageArea())));
            coverageAreaNode.remove("crs");

            addressNode.setAll((ObjectNode) mapper.readTree(writer.write(entity.getAddress())));
            addressNode.remove("crs");

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new PartnerResponseDTO(entity.getId(), entity.getTradingName(), entity.getOwnerName(),
                entity.getDocument(), coverageAreaNode, addressNode);

    }
}
