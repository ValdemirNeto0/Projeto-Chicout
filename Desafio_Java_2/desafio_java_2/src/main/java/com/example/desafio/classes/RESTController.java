package main.java.com.example.desafio.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pessoa")
class PessoaControlador {

    @Autowired
    PessoaRepository repository;

    //retorna todas as pessoas cadastradas
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        try {
            List<Pessoa> items = new ArrayList<Pessoa>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //retorna uma pessoa especifica por ID
    @GetMapping("{ID}")
    public ResponseEntity<Pessoa> getByID(@PathVariable("ID") String ID) {
        Optional<Pessoa> existingItemOptional = repository.findByID(ID);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Cria uma pessoa
    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa item) {
        try {
            Pessoa savedItem = repository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    //atualiza uma pessoa com ID especifico
    @PutMapping("{ID}")
    public ResponseEntity<Pessoa> update(@PathVariable("ID") entityIDType ID, @RequestBody Pessoa item) {
        Optional<Pessoa> existingItemOptional = repository.findByID(ID);
        if (existingItemOptional.isPresent()) {
            Pessoa existingItem = existingItemOptional.get();
            System.out.println("Atualizacao concluida");
            //existingItem.setSomeField(item.getSomeField());
            return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //dele uma pessoa com ID especifico
    @DeleteMapping("{ID}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("ID") entityIDType ID) {
        try {
            repository.deleteByID(ID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
