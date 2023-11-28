package br.com.consultorio.Controller;

import br.com.consultorio.DTO.RecepcionistaDTO;
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
    public ResponseEntity<RecepcionistaDTO> createRecepcionista(@RequestBody @Valid RecepcionistaDTO recepcionistaDTO){
        return new ResponseEntity<>(recepcionistaService.create(recepcionistaDTO), HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<RecepcionistaDTO> updateRecepcionista(@RequestBody @Valid RecepcionistaDTO recepcionistaDTO, @PathVariable Long id){
        return new ResponseEntity<>(recepcionistaService.update(recepcionistaDTO, id), HttpStatus.OK);
    }


    @DeleteMapping("excluir/{id}")
    public ResponseEntity<RecepcionistaDTO> deleteRecepcionista(@PathVariable Long id){
        recepcionistaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecepcionistaDTO> findRecepcionistaById(@PathVariable Long id){
        return new ResponseEntity<>(recepcionistaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<RecepcionistaDTO>> findRecepcionistasByNome(@RequestParam("nome") String nome){
        return new ResponseEntity<>(recepcionistaService.findByName(nome), HttpStatus.OK);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<RecepcionistaDTO>> findAllRecepcionistas(){
        return new ResponseEntity<>(recepcionistaService.findAll(), recepcionistaService.findAll().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }


}
