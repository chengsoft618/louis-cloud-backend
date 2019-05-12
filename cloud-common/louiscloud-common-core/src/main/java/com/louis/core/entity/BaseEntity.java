package com.louis.core.entity;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Eric
 * @date create in 2019/5/6
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> extends AbstractEntity<ID> {

    private static final long serialVersionUID = -2430797350775093998L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;


    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

    public String getIdToString(){
        return String.valueOf(id);
    }
}