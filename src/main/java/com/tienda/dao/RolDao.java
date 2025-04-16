package com.tienda.dao;
    
import com.tienda.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository <Rol,Long> {
//    public Rol findByUsername(String username);
//    public Rol findByUsernameAndPassword(String username, String password);
//    public Rol findByUsernameOrCorreo(String username, String correo);
//    public boolean existsByUsernameOrCorreo(String username, String correo);
    
}