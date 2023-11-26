package br.com.consultorio.Controller;

import br.com.consultorio.Record.MedicoRecord;
import br.com.consultorio.Record.PacienteRecord;
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
        public ResponseEntity<PacienteRecord> createPaciente(@RequestBody @Valid PacienteRecord pacienteRecord){
        return new ResponseEntity<>(pacienteService.create(pacienteRecord), HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<PacienteRecord> updatePaciente(@RequestBody @Valid PacienteRecord pacienteRecord, @PathVariable Long id){
        return new ResponseEntity<>(pacienteService.update(pacienteRecord, id), HttpStatus.OK);
    }


    @DeleteMapping("excluir/{id}")
    public ResponseEntity<PacienteRecord> deletePaciente(@PathVariable Long id){
        pacienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PacienteRecord> findPacienteById(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PacienteRecord>> findPacientesByNome(@PathVariable String nome){
        return new ResponseEntity<>(pacienteService.findByName(nome), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteRecord>> findAllPacientes(){
        return new ResponseEntity<>(pacienteService.findAll(), pacienteService.findAll().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }


}
