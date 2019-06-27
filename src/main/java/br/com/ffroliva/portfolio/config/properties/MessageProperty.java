package br.com.ffroliva.portfolio.config.properties;

public enum MessageProperty implements IMessageProperty {

    REGISTRO_DUPLICADO("registro.duplicado");

    private String[] args = {};

    private String key;

    private MessageProperty(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public String message() {
        return MessageSource.get().message(key, args);
    }

    public IMessageProperty bind(String... args) {
        this.args = args;
        return this;
    }
}

