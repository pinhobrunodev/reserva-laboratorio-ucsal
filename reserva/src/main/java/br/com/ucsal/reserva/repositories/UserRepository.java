package br.com.ucsal.reserva.repositories;

import br.com.ucsal.reserva.domains.Laboratory;
import br.com.ucsal.reserva.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

}
