package com.bolsaideas.springboot.app.auth.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.Claims;

/**
 * Interfaz encargada de proveer un diseño generico para cualquier
 * implementacion
 * 
 * @author paste
 *
 */
public interface JWTService {

    /**
     * Metodo encargado de crear el token
     * 
     * @param auth encargado de obtener los valores de username y roles
      * @return
     */
    public String create(Authentication auth) throws IOException;
    
    /**
     * Metodo encargado de validar el token
     * 
     * @param token a validar
     * @return true si contiene en la cabecera el token o false si no se obtiene
     */
    public boolean validate(String token);

    
    public Claims getClaims(String token);
    
    
    /**
     * Metodo encargado de obtener el usernamame del token
     * 
     * @param token de donde se obtendra el username
     * @return username obtenido del token
     */
    public String getUsername(String token);
    
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException ;
    
    public String resolve(String token);
}
