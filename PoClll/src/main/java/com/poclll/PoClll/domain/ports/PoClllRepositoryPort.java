package com.poclll.PoClll.domain.ports;

import com.poclll.PoClll.domain.Poclll;

import java.util.List;

public interface PoClllRepositoryPort {

    Poclll save(Poclll poclll);

    List<Poclll> findAll();

    void delete(Integer id);
}
