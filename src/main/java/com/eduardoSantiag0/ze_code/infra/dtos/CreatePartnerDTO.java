package com.eduardoSantiag0.ze_code.infra.dtos;

import com.fasterxml.jackson.databind.JsonNode;

public record CreatePartnerDTO(
    Long id,
    String tradingName,
    String ownerName,
    String document,
    JsonNode coverageArea,
    JsonNode address
) {
}
