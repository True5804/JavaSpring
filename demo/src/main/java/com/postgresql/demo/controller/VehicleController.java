package com.postgresql.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

import com.postgresql.demo.repo.VehicleRepo;
import com.postgresql.demo.model.Vehicles;
import com.postgresql.demo.utils.ApiResponse;
import com.postgresql.demo.utils.ErrorMessage;

@RestController
public class VehicleController {


   // Property to hold our repository
   private VehicleRepo vehicleRepo;

   // Constructor that receives the repository via dependency injection
   public VehicleController(VehicleRepo repo){
       this.vehicleRepo = repo;
   }

   // Get to /getVehicle that returns list of vehicles
   @GetMapping("/getVehicle")
   public ResponseEntity<ApiResponse<List<Vehicles>>> getAllVehicles() {
        try {
            // Fetch all vehicles
            List<Vehicles> vehicles = vehicleRepo.findAll();

            // Handle case where no vehicles are found
            if (vehicles.isEmpty()) {
                ApiResponse<List<Vehicles>> response = new ApiResponse<>(
                    ErrorMessage.DATA_NOT_FOUND.getCode(),
                    ErrorMessage.DATA_NOT_FOUND.getMsg(),
                    null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Return the list of vehicles with a success message
            ApiResponse<List<Vehicles>> response = new ApiResponse<>(
                ErrorMessage.SUCCESS.getCode(),
                ErrorMessage.SUCCESS.getMsg(),
                vehicles
            );
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Handle server error, return consistent type
            ApiResponse<List<Vehicles>> response = new ApiResponse<>(
                ErrorMessage.SERVER_ERROR.getCode(),
                ErrorMessage.SERVER_ERROR.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

   // Post to //newVehicle, takes in request body which must be of type Vehicles
   @PostMapping("/newVehicle")
   public ResponseEntity<ApiResponse<Object>> createVehicle(@RequestBody Vehicles vehicle) {
        if (vehicle.getLicenseplate() == null || vehicle.getOwner_name() == null || vehicle.getOwner_phone() == null
        || vehicle.getMileage() == 0 || vehicle.getBuild_date() == null || vehicle.getModified_date() == null) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.MISSING_PARAMETER.getCode(),
                ErrorMessage.MISSING_PARAMETER.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (vehicle.getMileage() < 0 || !vehicle.getLicenseplate().matches("^[A-Z0-9-]+$")) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.ERROR_IN_INPUT_PARAMETERS.getCode(),
                ErrorMessage.ERROR_IN_INPUT_PARAMETERS.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            vehicleRepo.save(vehicle);
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.SUCCESS.getCode(),
                ErrorMessage.SUCCESS.getMsg(),
                vehicle
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.SERVER_ERROR.getCode(),
                ErrorMessage.SERVER_ERROR.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
}

   // put to /updateVehicle/:id, takes in the body and url param id
   @PutMapping("/updateVehicle/{id}")
   public ResponseEntity<ApiResponse<Object>> updateVehicle(@RequestBody Vehicles updatedVehicle, @PathVariable Long id){
        Optional<Vehicles> vehicleOptional = vehicleRepo.findById(id);

        if (vehicleOptional.isEmpty()) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.DATA_NOT_FOUND.getCode(),
                ErrorMessage.DATA_NOT_FOUND.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
        if (updatedVehicle.getLicenseplate() == null || updatedVehicle.getOwner_name() == null || updatedVehicle.getOwner_phone() == null
            || updatedVehicle.getMileage() == 0 || updatedVehicle.getBuild_date() == null || updatedVehicle.getModified_date() == null) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.MISSING_PARAMETER.getCode(),
                ErrorMessage.MISSING_PARAMETER.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (updatedVehicle.getMileage() < 0 || !updatedVehicle.getLicenseplate().matches("^[A-Z0-9-]+$")) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.ERROR_IN_INPUT_PARAMETERS.getCode(),
                ErrorMessage.ERROR_IN_INPUT_PARAMETERS.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    
        try {
            Vehicles vehicle = vehicleOptional.get();
            vehicle.setLicenseplate(updatedVehicle.getLicenseplate());
            vehicle.setMileage(updatedVehicle.getMileage());
            vehicle.setOwner_name(updatedVehicle.getOwner_name());
            vehicle.setOwner_phone(updatedVehicle.getOwner_phone());
            vehicle.setBuild_date(updatedVehicle.getBuild_date());
            vehicle.setModified_date(updatedVehicle.getModified_date());
            vehicleRepo.save(vehicle);
    
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.SUCCESS.getCode(),
                ErrorMessage.SUCCESS.getMsg(),
                vehicle
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.SERVER_ERROR.getCode(),
                ErrorMessage.SERVER_ERROR.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
   }

   // delete request to /updateVehicle/:id, deletes vehicle based on id param
   @DeleteMapping("/deleteVehicle/{id}")
   public ResponseEntity<ApiResponse<Object>> deleteCat(@PathVariable Long id){
        Optional<Vehicles> vehicleOptional = vehicleRepo.findById(id);

        if (vehicleOptional.isEmpty()) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.DATA_NOT_FOUND.getCode(),
                ErrorMessage.DATA_NOT_FOUND.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
        try {
            vehicleRepo.deleteById(id);
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.SUCCESS.getCode(),
                ErrorMessage.SUCCESS.getMsg(),
                null
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = new ApiResponse<>(
                ErrorMessage.SERVER_ERROR.getCode(),
                ErrorMessage.SERVER_ERROR.getMsg(),
                null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
   }
}

