package br.com.consultorio.Repository;

import br.com.consultorio.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
