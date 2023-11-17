package br.com.consultorio;

import br.com.consultorio.Controller.MedicoController;
import br.com.consultorio.Model.Medico;
import br.com.consultorio.Repository.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsultorioApplicationTests {

	@Autowired
	private MedicoRepository medicoRepository;
	@Test
	void contextLoads() {
		Medico medico = new Medico();
		medico.setNome_completo("Priscila Fabrete");
		medico.setCRM("1234");
		medico.setTelefone("123455643");
		medicoRepository.save(medico);
	}

}
