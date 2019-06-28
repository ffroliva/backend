package br.com.ffroliva.portfolio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class BaseEntity<K extends Serializable> implements
		Serializable {

	private static final long serialVersionUID = 3470083738721419180L;

	public abstract K getId();

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
