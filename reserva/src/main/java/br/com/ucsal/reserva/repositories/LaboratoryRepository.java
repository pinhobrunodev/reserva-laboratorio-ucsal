package br.com.ucsal.reserva.repositories;

import br.com.ucsal.reserva.domains.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
}
