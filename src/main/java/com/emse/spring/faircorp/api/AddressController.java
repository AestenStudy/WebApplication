package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dto.ApiGouvAddressDto;
import com.emse.spring.faircorp.service.AddressSearchService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@Transactional
public class AddressController {
    @GetMapping("/{params}")
    public List<ApiGouvAddressDto> getAddresses(@PathVariable String params) {
        return new AddressSearchService(new RestTemplateBuilder()).findAddress(List.of(params));
    }

}
