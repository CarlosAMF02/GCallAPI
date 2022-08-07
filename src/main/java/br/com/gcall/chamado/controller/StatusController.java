package br.com.gcall.chamado.controller;

import br.com.gcall.chamado.entity.Status;
import br.com.gcall.chamado.models.StatusVM;
import br.com.gcall.chamado.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Status getStatus(@PathVariable(name = "id") long statusId) {
        return statusService.getStatusById(statusId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int createStatus(@RequestBody StatusVM statusVM) {
        return statusService.insertStatus(statusVM);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int updateStatus(@RequestBody StatusVM statusVM,@PathVariable(name = "id") long statusId) {
        return statusService.updateStatus(statusVM, statusId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int deleteStatus(@PathVariable(name = "id") long statusId) {
        return statusService.deleteStatus(statusId);
    }
}
