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

    public int insertStatus(StatusVM statusVM) {
        try {
            Status status = new Status(statusVM.getStatusName(), statusVM.getDescription());
            statusRepository.save(status);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int updateStatus(StatusVM statusVM, long statusId) {
        try {
            if (statusRepository.existsById(statusId)){
                Status status = new Status(statusId, statusVM.getStatusName(), statusVM.getDescription());
                statusRepository.save(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int deleteStatus(long statusId) {
        try {
            if (!statusRepository.existsById(statusId)) {
                return 2;
            }
            statusRepository.deleteById(statusId);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public Status getStatusById(long statusId) {
        Status status = null;
        try {
            status = statusRepository.findById(statusId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
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
