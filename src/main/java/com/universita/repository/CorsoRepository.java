package com.universita.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universita.entity.Corso;
public interface CorsoRepository extends JpaRepository<Corso, Long>{
	List<Corso> findByNomeContaining(String name);
	List<Corso> findByProfessoreNomeLike(String name);
}
