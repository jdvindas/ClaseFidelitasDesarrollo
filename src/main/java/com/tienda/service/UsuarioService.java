package com.tienda.service;

import com.tienda.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    
    // Se obtiene un listado de usuarios en un List
    public List<Usuario> getUsuarios();
    
   // Se obtiene un Usuario, a partir del id de un usuario
    public Usuario getUsuario(Usuario usuario);
    
    public Usuario getUsuarioPorUsernameYPassword (String username, String password);
    public Usuario getUsuarioPorUsernameOCorreo (String username, String correo);
    public boolean existsByUsernameOrCorreo(String username, String correo);
    
    // Se inserta un nuevo usuario si el id del usuario esta vacío
    // Se actualiza un usuario si el id del usuario NO esta vacío
    public void save(Usuario usuario, boolean creaRol);
    
    // Se elimina el usuario que tiene el id pasado por parámetro
    public void delete(Usuario usuario);
    
    public List<Usuario> findByPrecioBetweenOrderByDescription(double precioInf, double precioSub);
    
    public List<Usuario> metodoJPQL(double precioInf, double precioSub);
    
    public List<Usuario> metodoSQL(double precioInf, double precioSub);
}