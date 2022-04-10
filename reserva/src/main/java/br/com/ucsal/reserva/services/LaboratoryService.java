package br.com.ucsal.reserva.services;

import br.com.ucsal.reserva.controllers.models.RegisterLaboratoryInput;
import br.com.ucsal.reserva.services.models.RegisterLaboratoryResult;

public interface LaboratoryService {

    RegisterLaboratoryResult registerLaboratory(RegisterLaboratoryInput createLaboratoryInput);

}
