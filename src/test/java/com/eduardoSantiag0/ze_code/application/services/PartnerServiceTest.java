package com.eduardoSantiag0.ze_code.application.services;

import com.eduardoSantiag0.ze_code.application.errors.InvalidCredentialsException;
import com.eduardoSantiag0.ze_code.application.errors.NoPartnerCloseEnoughException;
import com.eduardoSantiag0.ze_code.application.errors.PartnerNotFoundException;
import com.eduardoSantiag0.ze_code.application.port.out.PartnerRepositoryPort;
import com.eduardoSantiag0.ze_code.infra.dtos.CreatePartnerDTO;
import com.eduardoSantiag0.ze_code.infra.dtos.PartnerResponseDTO;
import com.eduardoSantiag0.ze_code.infra.entities.PartnerEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PartnerServiceTest {

    private PartnerRepositoryPort repository;
    private PartnerService service;
    private JsonNode coverageAreaNode;
    private JsonNode addressNode;

    @BeforeEach
    void setup() throws JsonProcessingException {
        repository = mock(PartnerRepositoryPort.class);
        service = new PartnerService(repository);


        ObjectMapper mapper = new ObjectMapper();

        String coverageAreaJson = """
            {
                "type": "MultiPolygon",
                "coordinates": [
                    [[[30, 20], [45, 40], [10, 40], [30, 20]]],
                    [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]
                ]
            }
        """;

        String addressJson = """
            {
                "type": "Point",
                "coordinates": [-46.57421, -21.785741]
            }
        """;

        this.coverageAreaNode = mapper.readTree(coverageAreaJson);
        this.addressNode = mapper.readTree(addressJson);
    }

    @Test
    void createPartner_shouldReturnCreated_whenValidDTO() {
        CreatePartnerDTO dto = new CreatePartnerDTO(
                1L,
                "Test Partner",
                "1234567890",
                "1234057",
                coverageAreaNode,
                addressNode
        );

        String result = service.createPartner(dto);

        assertEquals("Created", result);
        verify(repository, times(1)).save(any(PartnerEntity.class));
    }

    @Test
    void createPartner_shouldThrowInvalidCredentials_whenTradingNameBlank() {
        CreatePartnerDTO dto = new CreatePartnerDTO(
                1L,
                "   ",
                "1234567890",
                null,
                null,
                null
        );

        assertThrows(InvalidCredentialsException.class, () -> service.createPartner(dto));
        verify(repository, never()).save(any());
    }


    @Test
    void findById_shouldThrowPartnerNotFound_whenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PartnerNotFoundException.class, () -> service.findById(1L));
    }


    @Test
    void findNearest_shouldThrowNoPartnerCloseEnough_whenNotFound() {
        when(repository.findWithin(10.0, 20.0)).thenReturn(Optional.empty());

        assertThrows(NoPartnerCloseEnoughException.class, () -> service.findNearest(10.0, 20.0));
    }
}