package br.com.ucsal.reserva.domains.pk;

import br.com.ucsal.reserva.domains.Laboratory;
import br.com.ucsal.reserva.domains.User;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class SchedulePK implements Serializable {


    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;

}
