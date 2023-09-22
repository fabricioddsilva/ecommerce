package com.ecommerce.services;

import com.ecommerce.models.Usuario;
import com.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository ur;

    public Usuario login (Usuario usuario) {
        Usuario login = ur.login(usuario.getNome(), usuario.getSenha());
        return login;
    }

}
