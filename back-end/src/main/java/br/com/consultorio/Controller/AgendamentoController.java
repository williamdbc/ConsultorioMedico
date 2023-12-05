package br.com.consultorio.Controller;

import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.DTO.MedicoDTO;
import br.com.consultorio.Service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping("/adicionar")
    public ResponseEntity<AgendamentoDTO> createMedico(@RequestBody @Valid AgendamentoDTO agendamentoDTO){
        return new ResponseEntity<>(agendamentoService.create(agendamentoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AgendamentoDTO>> findAllAgendamentos(){
        return new ResponseEntity<>(agendamentoService.findAll(), agendamentoService.findAll().isEmpty() ? HttpStatus.OK : HttpStatus.OK);
    }
}
