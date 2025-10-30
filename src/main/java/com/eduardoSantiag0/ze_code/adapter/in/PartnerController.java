package com.eduardoSantiag0.ze_code.adapter.in;


import com.eduardoSantiag0.ze_code.application.port.in.PartnerUseCase;
import com.eduardoSantiag0.ze_code.infra.dtos.CreatePartnerDTO;
import com.eduardoSantiag0.ze_code.infra.dtos.PartnerResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/partners")
public class PartnerController {

    private final PartnerUseCase partnerService;

    public PartnerController(PartnerUseCase partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping
    public ResponseEntity<String> createPartner(
            @RequestBody CreatePartnerDTO dto) {
        String response = partnerService.createPartner(dto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> getById(@PathVariable Long id) {
        var response = partnerService.findById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{lat}/{lon}")
    public ResponseEntity<PartnerResponseDTO> findNearestPartner(
            @PathVariable double lat,
            @PathVariable double lon
    ) {
        PartnerResponseDTO response = partnerService.findNearest(lat, lon);
        return ResponseEntity.ok(response);
    }


}
