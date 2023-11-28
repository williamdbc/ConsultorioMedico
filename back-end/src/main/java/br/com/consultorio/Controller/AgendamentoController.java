package br.com.consultorio.Controller;

import br.com.consultorio.Record.AgendamentoRecord;
import br.com.consultorio.Record.MedicoRecord;
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
    public ResponseEntity<AgendamentoRecord> createMedico(@RequestBody @Valid AgendamentoRecord agendamentoRecord){
        return new ResponseEntity<>(agendamentoService.create(agendamentoRecord), HttpStatus.CREATED);
    }
}
