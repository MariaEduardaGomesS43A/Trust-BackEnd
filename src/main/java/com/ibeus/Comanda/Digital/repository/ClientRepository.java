package com.ibeus.Comanda.Digital.repository;

import com.ibeus.Comanda.Digital.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
