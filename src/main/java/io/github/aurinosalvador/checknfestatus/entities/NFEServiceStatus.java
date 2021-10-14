package io.github.aurinosalvador.checknfestatus.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Aurino Salvador
 */

@Entity
@Table(name = "sys_nfe_status", indexes = {
        @Index(name = "nfe_updated_at_state_service", unique = true, columnList = "updated_at, state, service")
})
public class NFEServiceStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "updated_at",
            columnDefinition = "timestamp with time zone")
    private Date updatedAt;

    @Column(name = "state")
    private String state;

    @Column(name = "service")
    private String service;

    @Column(name = "status")
    private String status;

    public NFEServiceStatus() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
