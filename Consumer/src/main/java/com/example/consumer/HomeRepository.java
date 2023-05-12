package com.example.consumer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<HomeEntity, Long> {
}
