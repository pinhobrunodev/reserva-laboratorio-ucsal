package br.com.ucsal.reserva.services.models;

import br.com.ucsal.reserva.domains.Laboratory;
import br.com.ucsal.reserva.domains.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MakeScheduleResult {

    private UUID scheduleId;

    public MakeScheduleResult() {
    }

    public MakeScheduleResult(Schedule schedule){
        scheduleId = schedule.getScheduleId();
    }
}
