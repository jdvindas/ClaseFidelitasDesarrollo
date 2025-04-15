package com.tienda.dao;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository <Producto,Long> {
    public List<Producto> findByPrecioBetweenOrderByDescription(double precioInf, double precioSub);
    
    @Query(value="SELECT a FROM Producto a WHERE a.precio BETWEEN :precionInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoJPQL(double precioInf, double precioSub);
    
    @Query(nativeQuery=true, value="SELECT * FROM producto a WHERE a.precio BETWEEN :precionInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoSQL(double precioInf, double precioSub);
}