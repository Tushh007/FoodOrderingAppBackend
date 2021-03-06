package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "state")
@NamedQueries(
        {
                @NamedQuery(name = "allStates", query = "select s from StateEntity s"),
                @NamedQuery(name = "stateByUuid", query = "select s from StateEntity s where s.uuid=:uuid"),
                @NamedQuery(name = "stateById", query = "select s from StateEntity s where s.id=:id")

        }
)
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    @Size(max = 200)
    @NotNull
    private String uuid;

    @Column(name = "state_name")
    @Size(max = 30)
    private String stateName;

    public StateEntity(String uuid, String stateName) {
        this.uuid = uuid;
        this.stateName = stateName;
    }

    public StateEntity() {
    }

    public Long getId() {

        return Long.valueOf(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
