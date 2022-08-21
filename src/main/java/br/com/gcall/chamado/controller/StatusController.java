package br.com.gcall.chamado.controller;

import br.com.gcall.chamado.entity.Status;
import br.com.gcall.chamado.models.StatusVM;
import br.com.gcall.chamado.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public List<Status> getAllStatus() {
        return statusService.getStatus();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Status> getStatus(@PathVariable(name = "id") long statusId) {
        return ResponseEntity.of(statusService.getStatusById(statusId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StatusVM> createStatus(@RequestBody StatusVM statusVM) {
        statusService.insertStatus(statusVM);

        return ResponseEntity.status(HttpStatus.CREATED).body(statusVM);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StatusVM> updateStatus(@RequestBody StatusVM statusVM,@PathVariable(name = "id") long statusId) {
        int responseStatus = statusService.updateStatus(statusVM, statusId);

        if(responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(statusVM);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteStatus(@PathVariable(name = "id") long statusId) {
        int responseStatus = statusService.deleteStatus(statusId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
