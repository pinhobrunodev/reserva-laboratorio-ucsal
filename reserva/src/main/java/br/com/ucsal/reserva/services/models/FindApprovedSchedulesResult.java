package br.com.ucsal.reserva.services.models;

import br.com.ucsal.reserva.domains.Schedule;
import br.com.ucsal.reserva.domains.enums.State;
import br.com.ucsal.reserva.domains.pk.SchedulePK;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FindApprovedSchedulesResult {

    private UUID scheduleId;
    private Long requesterId;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private State state;
    private String reason;
    private Instant createdAt;

    public FindApprovedSchedulesResult(Schedule schedule){
        scheduleId = schedule.getScheduleId();
        requesterId = schedule.getRequester().getId();
        dateTimeStart = schedule.getDateTimeStart();
        dateTimeEnd = schedule.getDateTimeEnd();
        state = schedule.getState();
        reason = schedule.getReason();
        createdAt = schedule.getCreatedAt();
    }


}
