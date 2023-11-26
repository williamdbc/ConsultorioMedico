package br.com.consultorio.Repository;

import br.com.consultorio.Model.Paciente;
import br.com.consultorio.Model.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long> {
    @Query("SELECT r FROM Recepcionista r WHERE r.nome_completo LIKE %:nome%")
    List<Recepcionista> findRecepcionistasByName(@Param("nome") String nome);
}
