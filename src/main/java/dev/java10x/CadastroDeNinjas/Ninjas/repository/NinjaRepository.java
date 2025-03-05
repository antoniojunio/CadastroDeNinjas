package dev.java10x.CadastroDeNinjas.Ninjas.repository;

import dev.java10x.CadastroDeNinjas.Ninjas.model.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
}
