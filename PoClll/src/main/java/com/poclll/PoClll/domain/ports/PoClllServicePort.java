package com.poclll.PoClll.domain.ports;

import com.poclll.PoClll.domain.Poclll;

import java.util.List;

public interface PoClllServicePort {

    Poclll savePoc(Poclll poclll);

    List<Poclll> findAll();

    void deletePoc(Integer id);
}
