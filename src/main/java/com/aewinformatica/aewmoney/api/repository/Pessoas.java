package com.aewinformatica.aewmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aewinformatica.aewmoney.api.model.Pessoa;

public interface Pessoas extends JpaRepository<Pessoa, Long> {

}
