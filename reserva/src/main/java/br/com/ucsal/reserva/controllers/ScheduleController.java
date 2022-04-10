package br.com.ucsal.reserva.controllers;

import br.com.ucsal.reserva.controllers.models.RegisterScheduleInput;
import br.com.ucsal.reserva.controllers.models.ScheduleManagementInput;
import br.com.ucsal.reserva.services.ScheduleService;
import br.com.ucsal.reserva.services.models.FindApprovedSchedulesResult;
import br.com.ucsal.reserva.services.models.MakeScheduleResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ScheduleController {

    final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping(value = "/schedules/laboratory/{laboratoryId}/create")
    public ResponseEntity<MakeScheduleResult> makeSchedule(@PathVariable Long laboratoryId,
                                                           @RequestBody RegisterScheduleInput registerScheduleInput){

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.makeSchedule(registerScheduleInput,laboratoryId));
    }
    @PatchMapping(value = "/schedules/{scheduleId}/approve")
    public ResponseEntity<?> approveSchedule (@PathVariable UUID scheduleId, @RequestBody ScheduleManagementInput scheduleManagementInput){
        scheduleService.approveSchedule(scheduleManagementInput,scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body("Schedule Approved!");
    }

    @PatchMapping(value = "/schedules/{scheduleId}/disapprove")
    public ResponseEntity<?> disapproveSchedule (@PathVariable UUID scheduleId, @RequestBody ScheduleManagementInput scheduleManagementInput){
        scheduleService.disapproveSchedule(scheduleManagementInput,scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body("Schedule Disapproved!");
    }

    @GetMapping(value = "/schedules/approved")
    public ResponseEntity<Page<FindApprovedSchedulesResult>> findApprovedSchedules(Pageable pageable){
        return ResponseEntity.ok().body(scheduleService.findSchedulesApproved(pageable));
    }
    @GetMapping(value = "/schedules/pending")
    public ResponseEntity<Page<FindApprovedSchedulesResult>> findPendingSchedules(Pageable pageable){
        return ResponseEntity.ok().body(scheduleService.findSchedulesPending(pageable));
    }
    @GetMapping(value = "/schedules/recused")
    public ResponseEntity<Page<FindApprovedSchedulesResult>> findRecusedSchedules(Pageable pageable){
        return ResponseEntity.ok().body(scheduleService.findSchedulesRecused(pageable));
    }
}
