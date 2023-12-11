package br.com.consultorio.Controller;

import br.com.consultorio.DTO.ConsultaDTO;
import br.com.consultorio.Service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @PutMapping("/editar/{id}")
    public ResponseEntity<ConsultaDTO> updateConsulta(@RequestBody @Valid ConsultaDTO consultaDTO, @PathVariable Long id){
        return new ResponseEntity<>(consultaService.update(consultaDTO, id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> findConsultaById(@PathVariable Long id){
        return new ResponseEntity<>(consultaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/acessar")
    public ResponseEntity<List<ConsultaDTO>> acessarConsultas(@RequestParam String cpf,
                                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String birthDate){

        List<ConsultaDTO> consultas = consultaService.findConsultaByPaciente(cpf, birthDate);
        return new ResponseEntity<>(consultas, consultas.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ConsultaDTO>> findAllConsultas(){
        return new ResponseEntity<>(consultaService.findAll(), consultaService.findAll().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

}
