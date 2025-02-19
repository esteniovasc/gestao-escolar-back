package com.gestao.escolar.service;

import com.gestao.escolar.model.usuario.UserDetailsImpl;
import com.gestao.escolar.model.usuario.Usuario;
import com.gestao.escolar.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.getUsuarioByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new UserDetailsImpl(usuario);
    }
}