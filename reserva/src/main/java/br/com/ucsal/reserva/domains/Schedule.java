package br.com.ucsal.reserva.domains;

import br.com.ucsal.reserva.domains.enums.State;
import br.com.ucsal.reserva.domains.pk.SchedulePK;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_schedule")
public class Schedule implements Serializable {

    @EmbeddedId
    private SchedulePK scheduleEmbeddedPk = new SchedulePK();
    private UUID scheduleId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime dateTimeStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime dateTimeEnd;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(columnDefinition = "TEXT")
    private String reason;
    private Instant createdAt;

    public Schedule() {    }

    public Schedule(UUID id,User requester,Laboratory laboratory,LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, State state, String reason, Instant createdAt) {
        this.scheduleId = id;
        scheduleEmbeddedPk.setRequester(requester);
        scheduleEmbeddedPk.setLaboratory(laboratory);
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.state = state;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public UUID getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(UUID scheduleId) {
        this.scheduleId = scheduleId;
    }


    public SchedulePK getScheduleEmbeddedPk() {
        return scheduleEmbeddedPk;
    }

    public void setScheduleEmbeddedPk(SchedulePK scheduleEmbeddedPk) {
        this.scheduleEmbeddedPk = scheduleEmbeddedPk;
    }

    public User getRequester(){
        return scheduleEmbeddedPk.getRequester();
    }
    public void setUser(User requester){
        scheduleEmbeddedPk.setRequester(requester);
    }

    public Laboratory getLaboratory(){
        return scheduleEmbeddedPk.getLaboratory();
    }
    public void setLaboratory  (Laboratory laboratory){
        scheduleEmbeddedPk.setLaboratory(laboratory);
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
