package br.com.ucsal.reserva.repositories;

import br.com.ucsal.reserva.domains.Schedule;
import br.com.ucsal.reserva.domains.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    @Query("SELECT obj FROM Schedule obj WHERE obj.state = 'APROVADO'")
    Page<Schedule> findSchedulesApproved(Pageable pageable);
    @Query("SELECT obj FROM Schedule obj WHERE obj.state = 'PENDENTE'")
    Page<Schedule> findSchedulesPending(Pageable pageable);
    @Query("SELECT obj FROM Schedule obj WHERE obj.state = 'RECUSADO'")
    Page<Schedule> findSchedulesRecused(Pageable pageable);
    @Query("SELECT obj FROM Schedule obj WHERE obj.scheduleId = :scheduleId")
    Optional<Schedule> findByScheduleId(UUID scheduleId);
}
