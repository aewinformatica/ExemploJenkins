package com.aewinformatica.aewmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aewinformatica.aewmoney.api.model.Categoria;

@Repository
public interface Categorias extends JpaRepository<Categoria, Long>{

}
