package br.com.consultorio;

import br.com.consultorio.Controller.MedicoController;
import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.DTO.MedicoDTO;
import br.com.consultorio.Model.Agendamento;
import br.com.consultorio.Model.Medico;
import br.com.consultorio.Repository.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsultorioApplicationTests {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	@Test
	void contextLoads() {
		Medico medico = new Medico();
		medico.setId(1L);
		medico.setNome_completo("wILLia");
		medicoRepository.save(medico);
		
		MedicoDTO medicoDTO = new MedicoDTO();
		medicoDTO.setId(1L);
		medicoDTO.setNome_completo("wILLia");
		
		
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
		agendamentoDTO.setMedico(medicoDTO);
		
		
		
		Medico medico = new Medico();
		medico.setId(1L);
		medico.setNome_completo("wILLia");
		medicoRepository.save(medico);
	}

}
