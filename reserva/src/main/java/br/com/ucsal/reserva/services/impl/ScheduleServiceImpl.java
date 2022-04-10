package br.com.ucsal.reserva.services.impl;

import br.com.ucsal.reserva.controllers.models.RegisterScheduleInput;
import br.com.ucsal.reserva.controllers.models.ScheduleManagementInput;
import br.com.ucsal.reserva.domains.Schedule;
import br.com.ucsal.reserva.domains.enums.Role;
import br.com.ucsal.reserva.domains.enums.State;
import br.com.ucsal.reserva.repositories.LaboratoryRepository;
import br.com.ucsal.reserva.repositories.ScheduleRepository;
import br.com.ucsal.reserva.repositories.UserRepository;
import br.com.ucsal.reserva.services.ScheduleService;
import br.com.ucsal.reserva.services.exceptions.ForbiddenException;
import br.com.ucsal.reserva.services.exceptions.ResourceNotFoundException;
import br.com.ucsal.reserva.services.models.FindApprovedSchedulesResult;
import br.com.ucsal.reserva.services.models.MakeScheduleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
public class ScheduleServiceImpl implements  ScheduleService {

    final LaboratoryRepository laboratoryRepository;
    final UserRepository userRepository;
    final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(LaboratoryRepository laboratoryRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.laboratoryRepository = laboratoryRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public MakeScheduleResult makeSchedule(RegisterScheduleInput registerScheduleInput, Long laboratoryId) {
            var laboratory = laboratoryRepository.findById(laboratoryId).orElseThrow(()-> new ResourceNotFoundException("Laboratory not found"));
            var user = userRepository.findById(registerScheduleInput.getRequesterId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
                log.info("schedule to be persisted : {}",registerScheduleInput);
                return new MakeScheduleResult(
                        scheduleRepository.save(new Schedule(
                                UUID.randomUUID(),user,laboratory,registerScheduleInput.getDateTimeStart(),
                                registerScheduleInput.getDateTimeEnd(), State.PENDENTE,registerScheduleInput.getReason(),
                                Instant.now()
                        ))
                );
    }

    @Override
    public void approveSchedule(ScheduleManagementInput scheduleManagementInput, UUID scheduleId) {
        var schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(()-> new ResourceNotFoundException("Schedule not found"));
        var user = userRepository.findById(scheduleManagementInput.getRequesterId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        if (user.getRole().equals(Role.SOLICITANTE)) throw  new ForbiddenException("Access Denied");
        schedule.setState(State.APROVADO);
        scheduleRepository.save(schedule);

    }

    @Override
    public void disapproveSchedule(ScheduleManagementInput scheduleManagementInput, UUID scheduleId) {
        var schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(()-> new ResourceNotFoundException("Schedule not found"));
        var user = userRepository.findById(scheduleManagementInput.getRequesterId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        if (user.getRole().equals(Role.SOLICITANTE)) throw  new ForbiddenException("Access Denied");
        schedule.setState(State.RECUSADO);
        scheduleRepository.save(schedule);

    }

    @Override
    public Page<FindApprovedSchedulesResult> findSchedulesApproved(Pageable pageable) {
        Page<Schedule> result = scheduleRepository.findSchedulesApproved(pageable);
        return result.map(FindApprovedSchedulesResult::new);
    }

    @Override
    public Page<FindApprovedSchedulesResult> findSchedulesPending(Pageable pageable) {
        Page<Schedule> result = scheduleRepository.findSchedulesPending(pageable);
        return result.map(FindApprovedSchedulesResult::new);
    }

    @Override
    public Page<FindApprovedSchedulesResult> findSchedulesRecused(Pageable pageable) {
        Page<Schedule> result = scheduleRepository.findSchedulesRecused(pageable);
        return result.map(FindApprovedSchedulesResult::new);
    }
}
