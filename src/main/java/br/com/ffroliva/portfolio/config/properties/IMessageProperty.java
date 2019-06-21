package br.com.ffroliva.portfolio.config.properties;

import java.io.Serializable;

public interface IMessageProperty extends Serializable {

    String key();

    String message();

    IMessageProperty bind(String... args) ;
}

