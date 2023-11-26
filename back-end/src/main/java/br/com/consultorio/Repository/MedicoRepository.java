package br.com.consultorio.Repository;

import br.com.consultorio.Model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("SELECT m FROM Medico m WHERE m.nome_completo LIKE %:nome%")
    List<Medico> findMedicosByName(@Param("nome") String nome);

}
