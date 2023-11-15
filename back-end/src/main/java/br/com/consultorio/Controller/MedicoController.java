package br.com.consultorio.Controller;

import br.com.consultorio.Model.Medico;
import br.com.consultorio.Record.MedicoRecord;
import br.com.consultorio.Service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping("/adicionar")
    public ResponseEntity<MedicoRecord> createMedico(@RequestBody @Valid MedicoRecord medicoRecord){
        return new ResponseEntity<>(medicoService.create(medicoRecord), HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<MedicoRecord> updateMedico(@RequestBody @Valid MedicoRecord medicoRecord, @PathVariable Long id){
        return new ResponseEntity<>(medicoService.update(medicoRecord, id), HttpStatus.OK);
    }


    @DeleteMapping("excluir/{id}")
    public ResponseEntity<MedicoRecord> deleteMedico(@PathVariable Long id){
        medicoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicoRecord> findMedicoById(@PathVariable Long id){
        return new ResponseEntity<>(medicoService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<MedicoRecord>> findAllMedicos(){
        return new ResponseEntity<>(medicoService.findAll(), medicoService.findAll().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }


}
