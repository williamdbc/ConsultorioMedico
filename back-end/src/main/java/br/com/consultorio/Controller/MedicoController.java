package br.com.consultorio.Controller;

import br.com.consultorio.DTO.MedicoDTO;
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
    public ResponseEntity<MedicoDTO> createMedico(@RequestBody @Valid MedicoDTO medicoDTO){
        return new ResponseEntity<>(medicoService.create(medicoDTO), HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<MedicoDTO> updateMedico(@RequestBody @Valid MedicoDTO medicoDTO, @PathVariable Long id){
        return new ResponseEntity<>(medicoService.update(medicoDTO, id), HttpStatus.OK);
    }


    @DeleteMapping("excluir/{id}")
    public ResponseEntity<MedicoDTO> deleteMedico(@PathVariable Long id){
        medicoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findMedicoById(@PathVariable Long id){
        return new ResponseEntity<>(medicoService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<MedicoDTO>> findMedicosByNome(@RequestParam("nome") String nome){
        return new ResponseEntity<>(medicoService.findByName(nome), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MedicoDTO>> findAllMedicos(){
        return new ResponseEntity<>(medicoService.findAll(), medicoService.findAll().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }


}
