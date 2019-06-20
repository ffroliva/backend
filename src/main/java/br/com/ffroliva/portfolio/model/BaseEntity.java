package br.com.ffroliva.portfolio.model;

import java.io.Serializable;

public abstract class BaseEntity<K extends Serializable> {

    abstract K getId();
}
