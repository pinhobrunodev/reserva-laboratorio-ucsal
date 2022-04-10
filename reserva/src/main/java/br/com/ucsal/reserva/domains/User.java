package br.com.ucsal.reserva.domains;

import br.com.ucsal.reserva.domains.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_user")
public  class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;


    @OneToMany(mappedBy = "scheduleEmbeddedPk.requester")
    private List<Schedule> schedules = new ArrayList<>();

}
