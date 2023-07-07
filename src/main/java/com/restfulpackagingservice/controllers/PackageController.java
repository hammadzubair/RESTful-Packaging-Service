package com.restfulpackagingservice.controllers;

import com.restfulpackagingservice.model.Item;
import com.restfulpackagingservice.request.PackageRequest;
import com.restfulpackagingservice.response.PackageResponse;
import com.restfulpackagingservice.services.PackageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PackageController {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping("/package")
    public ResponseEntity<PackageResponse> packItems(@RequestBody PackageRequest packageRequest) {
        packageService.validateRequest(packageRequest);

        List<Item> items = packageRequest.getItems();
        List<Integer> selectedItems = packageService.findOptimalItems(items, packageRequest.getMaxWeight());

        if (selectedItems.isEmpty()) {
            return ResponseEntity.ok(new PackageResponse("No items can be placed in the package."));
        }

        PackageResponse response = new PackageResponse(selectedItems);
        return ResponseEntity.ok(response);
    }

}

