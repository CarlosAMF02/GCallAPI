package br.com.gcall.chamado.services;

import br.com.gcall.chamado.entity.Status;
import br.com.gcall.chamado.models.StatusVM;
import br.com.gcall.chamado.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public void insertStatus(StatusVM statusVM) {
        try {
            Status status = new Status(statusVM.getStatusName(), statusVM.getDescription());
            statusRepository.save(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int updateStatus(StatusVM statusVM, long statusId) {
        try {
            if (!statusRepository.existsById(statusId)) return 1;

            Status status = new Status(statusId, statusVM.getStatusName(), statusVM.getDescription());
            statusRepository.save(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteStatus(long statusId) {
        try {
            if (!statusRepository.existsById(statusId)) return 1;

            statusRepository.deleteById(statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Optional<Status> getStatusById(long statusId) {
        return statusRepository.findById(statusId);
    }

    public List<Status> getStatus() {
        List<Status> statusList = null;
        try {
            statusList = statusRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusList;
    }
}
