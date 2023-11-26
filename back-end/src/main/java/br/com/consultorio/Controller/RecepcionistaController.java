package br.com.consultorio.Controller;

import br.com.consultorio.Record.PacienteRecord;
import br.com.consultorio.Record.RecepcionistaRecord;
import br.com.consultorio.Service.RecepcionistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recepcionistas")
public class RecepcionistaController {

    private final RecepcionistaService recepcionistaService;

    @PostMapping("/adicionar")
    public ResponseEntity<RecepcionistaRecord> createRecepcionista(@RequestBody @Valid RecepcionistaRecord recepcionistaRecord){
        return new ResponseEntity<>(recepcionistaService.create(recepcionistaRecord), HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<RecepcionistaRecord> updateRecepcionista(@RequestBody @Valid RecepcionistaRecord recepcionistaRecord, @PathVariable Long id){
        return new ResponseEntity<>(recepcionistaService.update(recepcionistaRecord, id), HttpStatus.OK);
    }


    @DeleteMapping("excluir/{id}")
    public ResponseEntity<RecepcionistaRecord> deleteRecepcionista(@PathVariable Long id){
        recepcionistaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecepcionistaRecord> findRecepcionistaById(@PathVariable Long id){
        return new ResponseEntity<>(recepcionistaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<RecepcionistaRecord>> findRecepcionistasByNome(@PathVariable String nome){
        return new ResponseEntity<>(recepcionistaService.findByName(nome), HttpStatus.OK);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<RecepcionistaRecord>> findAllRecepcionistas(){
        return new ResponseEntity<>(recepcionistaService.findAll(), recepcionistaService.findAll().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }


}
