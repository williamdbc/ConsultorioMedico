package br.com.consultorio.Controller;

import br.com.consultorio.DTO.AgendamentoDTO;
import br.com.consultorio.Service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<AgendamentoDTO> createAgendamento(@RequestBody @Valid AgendamentoDTO agendamentoDTO){
        return new ResponseEntity<>(agendamentoService.create(agendamentoDTO), HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<AgendamentoDTO> updateAgendamento(@RequestBody @Valid AgendamentoDTO agendamentoDTO, @PathVariable Long id){
        return new ResponseEntity<>(agendamentoService.update(agendamentoDTO, id), HttpStatus.CREATED);
    }


    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<AgendamentoDTO> deleteAgendamento(@PathVariable Long id){
        agendamentoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> findAgendamentoById(@PathVariable Long id){
        return new ResponseEntity<>(agendamentoService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<AgendamentoDTO>> findAllAgendamentos(){
        return new ResponseEntity<>(agendamentoService.findAll(), agendamentoService.findAll().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AgendamentoDTO>> findAgendamentoBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String startDateTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String endDateTime) {

        List<AgendamentoDTO> agendamentos = agendamentoService.findSchedulesBetweenDates(startDateTime, endDateTime);

        return new ResponseEntity<>(agendamentos, agendamentos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

}
