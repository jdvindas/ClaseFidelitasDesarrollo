package com.tienda.service.impl;

import com.tienda.dao.UsuarioDao;
import com.tienda.domain.Usuario;
import com.tienda.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        var lista = usuarioDao.findAll();
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername (String username) {
        return usuarioDao.findUsuarioPorUsername(username);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword (String username, String password) {
        return usuarioDao.findUsuarioPorUsernameYPassword (username, password);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo (String username, String correo) {
        return usuarioDao.findUsuarioPorUsernameOCorreo (username, correo);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsernameOrCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrCorreo(username, correo);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean creaRol) {
        usuario = usuarioDao.save(usuario);
        if(creaRol){
            Rol rol = new Rol();
            rol.setIdUsuario(usuario.getIdUsuario());
            rol.setNombre("ROLE_USER");
            rolDao.save(rol);
        }
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}