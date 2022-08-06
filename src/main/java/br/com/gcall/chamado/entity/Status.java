package br.com.gcall.chamado.entity;

import javax.persistence.*;

@Entity(name = "T_GC_STATUS")
public class Status {
    @Id
    @Column(name = "cd_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nm_status", length = 40, nullable = false)
    private String statusName;
    @Column(name = "ds_status")
    private String description;

    public Status(long id, String statusName, String description) {
        this.id = id;
        this.statusName = statusName;
        this.description = description;
    }

    public Status() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
