package br.com.ucsal.reserva.services;

import br.com.ucsal.reserva.controllers.models.RegisterScheduleInput;
import br.com.ucsal.reserva.controllers.models.ScheduleManagementInput;
import br.com.ucsal.reserva.services.models.FindApprovedSchedulesResult;
import br.com.ucsal.reserva.services.models.MakeScheduleResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ScheduleService {

    /*
        uri -> laboratoryId
     */
    MakeScheduleResult makeSchedule(RegisterScheduleInput registerScheduleInput, Long laboratoryId);
    void approveSchedule(ScheduleManagementInput scheduleManagementInput, UUID scheduleId);
    void disapproveSchedule(ScheduleManagementInput scheduleManagementInput , UUID scheduleId);
    Page<FindApprovedSchedulesResult> findSchedulesApproved(Pageable pageable);
    Page<FindApprovedSchedulesResult> findSchedulesPending(Pageable pageable);
    Page<FindApprovedSchedulesResult> findSchedulesRecused(Pageable pageable);
}
