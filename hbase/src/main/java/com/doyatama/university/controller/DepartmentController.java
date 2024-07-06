package com.doyatama.university.controller;

import com.doyatama.university.model.Department;
import com.doyatama.university.payload.ApiResponse;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.DepartmentRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.service.DepartmentService;
import com.doyatama.university.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private DepartmentService departmentService = new DepartmentService();

    @GetMapping
    public PagedResponse<Department> getDepartments(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return departmentService.getAllDepartment(page, size);
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) throws IOException {
        Department department = departmentService.createDepartment(departmentRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{departmentId}")
                .buildAndExpand(department.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Department Created Successfully"));
    }

    @GetMapping("/{departmentId}")
    public DefaultResponse<Department> getDepartmentById(@PathVariable String departmentId) throws IOException {
        return departmentService.getDepartmentById(departmentId);
    }


    @PutMapping("/{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable String departmentId,
                                              @Valid @RequestBody DepartmentRequest departmentRequest) throws IOException {
        Department department = departmentService.updateDepartment(departmentId, departmentRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{departmentId}")
                .buildAndExpand(department.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Department Updated Successfully"));
    }

    @DeleteMapping("/{departmentId}")
    public HttpStatus deleteDepartment(@PathVariable (value = "departmentId") String departmentId) throws IOException {
        departmentService.deleteDepartmentById(departmentId);
        return HttpStatus.FORBIDDEN;
    }
}
