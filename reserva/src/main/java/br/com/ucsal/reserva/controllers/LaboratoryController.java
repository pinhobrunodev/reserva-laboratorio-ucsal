package br.com.ucsal.reserva.controllers;

import br.com.ucsal.reserva.controllers.models.RegisterLaboratoryInput;
import br.com.ucsal.reserva.services.LaboratoryService;
import br.com.ucsal.reserva.services.models.RegisterLaboratoryResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LaboratoryController {


    final LaboratoryService laboratoryService;

    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    @PostMapping(value = "/laboratories/create")
    public ResponseEntity<RegisterLaboratoryResult> registerLaboratory(@RequestBody RegisterLaboratoryInput registerLaboratoryInput){
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratoryService.registerLaboratory(registerLaboratoryInput));
    }


}
