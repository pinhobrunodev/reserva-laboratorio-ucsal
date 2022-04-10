package br.com.ucsal.reserva.services.models;

import br.com.ucsal.reserva.domains.Laboratory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RegisterLaboratoryResult {

    private Long laboratoryId;

    public RegisterLaboratoryResult() {
    }

    public  RegisterLaboratoryResult(Laboratory laboratory){
        laboratoryId = laboratory.getId();
    }
}
