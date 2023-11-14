package com.teamfresh.voccompensation.voc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocRepository extends JpaRepository<Voc, Long> {
}
