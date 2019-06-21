package br.com.ffroliva.portfolio.model;

import java.io.Serializable;

public abstract class BaseEntity<K extends Serializable> {

    abstract K getId();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BaseEntity))
            return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        String entidade = this.getClass().getSimpleName();
        return "Entidade [ " + entidade + " ] {" + "id=" + getId() + '}';
    }
}
