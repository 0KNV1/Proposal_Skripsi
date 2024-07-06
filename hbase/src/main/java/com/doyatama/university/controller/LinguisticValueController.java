package com.doyatama.university.controller;

import com.doyatama.university.model.LinguisticValue;
import com.doyatama.university.payload.ApiResponse;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.LinguisticValueRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.service.LinguisticValueService;
import com.doyatama.university.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

/**
 * @author alfa
 */
@RestController
@RequestMapping("/api/linguistic-value")
public class LinguisticValueController {
    private LinguisticValueService linguisticValueService = new LinguisticValueService();

    @GetMapping
    public PagedResponse<LinguisticValue> getLinguisticValues(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                              @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return linguisticValueService.getAllLinguisticValue(page,size);
    }

    @PostMapping
    public ResponseEntity<?> createLinguisticValue(@Valid @RequestBody LinguisticValueRequest linguisticValueRequest) throws IOException {
        LinguisticValue linguisticValue = linguisticValueService.createLinguisticValue(linguisticValueRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{linguisticValueId}")
                .buildAndExpand(linguisticValue.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Linguistic Value Created Successfully"));
    }

    @GetMapping("/{linguisticValueId}")
    public DefaultResponse<LinguisticValue> getLinguisticValueById(@PathVariable String linguisticValueId) throws IOException {
        return linguisticValueService.getLinguisticValueById(linguisticValueId);
    }
    @PutMapping("/{linguisticValueId}")
    public ResponseEntity<?> updateLinguisticValue(@PathVariable String linguisticValueId,
                                          @Valid @RequestBody LinguisticValueRequest linguisticValueRequest) throws IOException {
        LinguisticValue linguisticValue = linguisticValueService.updateLinguisticValue(linguisticValueId, linguisticValueRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{linguisticValueId}")
                .buildAndExpand(linguisticValue.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Linguistic Value Updated Successfully"));
    }
    @DeleteMapping("/{linguisticValueId}")
    public ResponseEntity<?> deleteLinguisticValue(@PathVariable String linguisticValueId) throws IOException {
        linguisticValueService.deleteLinguisticValueById(linguisticValueId);

        return new ResponseEntity<>(new ApiResponse(true, "Linguistic Value Deleted Successfully"), HttpStatus.OK);
    }
}
