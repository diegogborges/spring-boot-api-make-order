package br.com.order.api.controller;

import br.com.order.api.exception.ResourceNotFoundException;
import br.com.order.api.model.Client;
import br.com.order.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(clientRepository.findAll(), null, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<?> findClientById(@PathVariable Long id) {
        return new ResponseEntity<>(clientRepository.findById(id), null, HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<?> save(@RequestBody @Valid Client client, BindingResult result) {

        if (result.hasErrors())
            return ResponseEntity.badRequest().body("Erro");

        return new ResponseEntity<>(clientRepository.save(client), null, HttpStatus.OK);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<?> edit(@RequestBody @Valid Client client, @PathVariable long id) {

        Optional<Client> clientOptional = clientRepository.findById(id);

        if (!clientOptional.isPresent())
            throw new ResourceNotFoundException("Client not found for this id :: " + id);

        client.setIdClient(id);

        return new ResponseEntity<>(clientRepository.save(client), null, HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));

        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
