package br.com.consultorio.Controller;

import br.com.consultorio.DTO.PacienteDTO;
import br.com.consultorio.Service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping("/adicionar")
        public ResponseEntity<PacienteDTO> createPaciente(@RequestBody @Valid PacienteDTO pacienteDTO){
        return new ResponseEntity<>(pacienteService.create(pacienteDTO), HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@RequestBody @Valid PacienteDTO pacienteDTO, @PathVariable Long id){
        return new ResponseEntity<>(pacienteService.update(pacienteDTO, id), HttpStatus.OK);
    }


    @DeleteMapping("excluir/{id}")
    public ResponseEntity<PacienteDTO> deletePaciente(@PathVariable Long id){
        pacienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findPacienteById(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<PacienteDTO>> findPacientesByNome(@RequestParam("nome") String nome){
        return new ResponseEntity<>(pacienteService.findByName(nome), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteDTO>> findAllPacientes(){
        return new ResponseEntity<>(pacienteService.findAll(), pacienteService.findAll().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }


}
