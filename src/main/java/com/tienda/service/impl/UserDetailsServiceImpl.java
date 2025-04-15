package com.tienda.service.impl;

import com.google.cloud.storage.Acl.User;
import com.tienda.dao.UsuarioDao;
import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
import com.tienda.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {
    @Autowire
    private UsuarioDao usuarioDao;
    
    @Autowire
    private HttpSession session;
            
    @Override
    @Transactional (readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null){
            throw new UsernameNotFoundException(username);
        }
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        
        var roles = new ArrayList<GrantedAuthority>();
        
        for (Rol rol : usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority("ROL_"+rol.getNombre())); 
        }
        
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
