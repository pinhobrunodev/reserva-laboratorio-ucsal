package br.com.ucsal.reserva.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_laboratory")
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String block;
    private String number;
    private Boolean isActive;
    @Column(columnDefinition = "TEXT")
    private String equipments;

    @OneToMany(mappedBy = "scheduleEmbeddedPk.laboratory")
    private List<Schedule> schedules = new ArrayList<>();
}

