package br.com.ffroliva.portfolio.model;

import java.io.Serializable;

abstract class BaseEntity<K extends Serializable> {

    abstract K getId();
}
