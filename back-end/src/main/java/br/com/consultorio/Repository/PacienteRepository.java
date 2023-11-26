package br.com.consultorio.Repository;

import br.com.consultorio.Model.Medico;
import br.com.consultorio.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE p.nome_completo LIKE %:nome%")
    List<Paciente> findPacientesByName(@Param("nome") String nome);
}
