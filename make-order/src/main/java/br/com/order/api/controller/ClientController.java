package br.com.order.api.controller;

import br.com.order.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository client;

    @GetMapping("/client")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(client.findAll(), null, HttpStatus.OK);
    }

}
