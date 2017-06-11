package com.lantushenko.experimental.stub.dao.impl;

import com.lantushenko.experimental.stub.dao.AbstractEntity;
import com.lantushenko.experimental.stub.exceptions.GeneralRuntimeException;

public class AbstractEntityImpl<T extends AbstractEntity> implements AbstractEntity {

    private Long id;
    private final Class<T> entityType;

    public AbstractEntityImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isTransient() {
        return null == id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!AbstractEntityImpl.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        if (!((AbstractEntityImpl) obj).getEntityType().isAssignableFrom(entityType)) {
            return false;
        }
        AbstractEntity other = (AbstractEntity) obj;
        if (isTransient() || other.isTransient()) {
            throw new GeneralRuntimeException(
                    "An attemp to compare transient entities with default Equals method has been performed");
        }
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}
