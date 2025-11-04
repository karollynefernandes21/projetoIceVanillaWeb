
package com.dev.IceVanilla.repository;

import com.dev.IceVanilla.model.Sorvetes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorvetesRepository extends JpaRepository<Sorvetes, Long> {}
