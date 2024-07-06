package com.doyatama.university.controller;

import com.doyatama.university.model.StudyProgram;
import com.doyatama.university.model.RPS;
import com.doyatama.university.payload.ApiResponse;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.RPSRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.service.RPSService;
import com.doyatama.university.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/rps")
public class RPSController {
    private RPSService rpsService = new RPSService();
    
    @GetMapping
    public PagedResponse<RPS> getRPSs(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                              @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return rpsService.getAllRPS(page, size);
    }

    @PostMapping
    public ResponseEntity<?> createRPS(@Valid @RequestBody RPSRequest rpsRequest) throws IOException {
        RPS rps = rpsService.createRPS(rpsRequest);

        if(rps == null){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Please check relational ID"));
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{rpsId}")
                    .buildAndExpand(rps.getId()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "RPS Created Successfully"));
        }
    }

    @GetMapping("/{rpsId}")
    public DefaultResponse<RPS> getRPSById(@PathVariable String rpsId) throws IOException {
        return rpsService.getRPSById(rpsId);
    }


    @PutMapping("/{rpsId}")
    public ResponseEntity<?> updateRPS(@PathVariable String rpsId,
                                           @Valid @RequestBody RPSRequest rpsRequest) throws IOException {
        RPS rps = rpsService.updateRPS(rpsId, rpsRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{rpsId}")
                .buildAndExpand(rps.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "RPS Updated Successfully"));
    }

    @DeleteMapping("/{rpsId}")
    public HttpStatus deleteRPS(@PathVariable (value = "rpsId") String rpsId) throws IOException {
        rpsService.deleteRPSById(rpsId);
        return HttpStatus.FORBIDDEN;
    }
}
