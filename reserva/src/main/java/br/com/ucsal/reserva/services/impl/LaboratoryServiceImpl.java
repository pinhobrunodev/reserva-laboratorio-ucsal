package br.com.ucsal.reserva.services.impl;

import br.com.ucsal.reserva.controllers.models.RegisterLaboratoryInput;
import br.com.ucsal.reserva.domains.Laboratory;
import br.com.ucsal.reserva.domains.enums.Role;
import br.com.ucsal.reserva.repositories.LaboratoryRepository;
import br.com.ucsal.reserva.repositories.UserRepository;
import br.com.ucsal.reserva.services.LaboratoryService;
import br.com.ucsal.reserva.services.exceptions.ForbiddenException;
import br.com.ucsal.reserva.services.exceptions.ResourceNotFoundException;
import br.com.ucsal.reserva.services.models.RegisterLaboratoryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class LaboratoryServiceImpl implements LaboratoryService {


    final LaboratoryRepository laboratoryRepository;
    final UserRepository userRepository;

    public LaboratoryServiceImpl(LaboratoryRepository laboratoryRepository, UserRepository userRepository) {
        this.laboratoryRepository = laboratoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RegisterLaboratoryResult registerLaboratory(RegisterLaboratoryInput createLaboratoryInput) {
        var laboratoryEntity = new Laboratory();
        try {
            var user = userRepository.getById(createLaboratoryInput.getRequesterId());
            if (user.getRole().equals(Role.SOLICITANTE)) {
                throw  new ForbiddenException("Access Denied");
            }
            if (user.getRole().equals(Role.GESTOR)){
                log.info("laboratory to be persisted : {}",createLaboratoryInput);
                laboratoryEntity.setBlock(createLaboratoryInput.getBlock());
                laboratoryEntity.setNumber(createLaboratoryInput.getNumber());
                laboratoryEntity.setIsActive(Boolean.TRUE);
                laboratoryEntity.setEquipments(createLaboratoryInput.getEquipments());
            }
        }catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("User not found");
        }
        return new RegisterLaboratoryResult(laboratoryRepository.save(laboratoryEntity));
    }
}
