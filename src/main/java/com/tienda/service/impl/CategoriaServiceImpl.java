package com.tienda.service.impl;

import com.tienda.dao.CategoriaDao;
import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaDao categoriaDao;
    
    @Transactional(readOnly = true) //Para manejar transacciones de sólo lectura
    public List<Categoria> getCategorias() {
        return (List<Categoria>) categoriaDao.findAll();
    }

    @Override
    public List<Categoria> getCategorias(boolean activos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}