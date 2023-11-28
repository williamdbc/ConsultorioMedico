package br.com.consultorio.Controller;

import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.Service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping("/adicionar")
    public ResponseEntity<AgendamentoDTO> createMedico(@RequestBody @Valid AgendamentoDTO agendamentoDTO){
        return new ResponseEntity<>(agendamentoService.create(agendamentoDTO), HttpStatus.CREATED);
    }


}
