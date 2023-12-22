package com.bolsaideas.springboot.app.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityMixin {

    // Marca que indica que es el constructor por defecto cuando se creen los objetos authorities apartir del JSON
    @JsonCreator
    public SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) { }

    
    
}
