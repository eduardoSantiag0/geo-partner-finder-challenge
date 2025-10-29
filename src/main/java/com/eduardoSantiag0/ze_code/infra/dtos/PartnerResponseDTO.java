package com.eduardoSantiag0.ze_code.infra.dtos;

import com.fasterxml.jackson.databind.node.ObjectNode;

public record PartnerResponseDTO(
    Long id,
    String tradingName,
    String ownerName,
    String document,
    ObjectNode coverageArea,
    ObjectNode address

) {

}
