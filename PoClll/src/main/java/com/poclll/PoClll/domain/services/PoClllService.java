package com.poclll.PoClll.domain.services;

import com.poclll.PoClll.domain.Poclll;
import com.poclll.PoClll.domain.ports.PoClllRepositoryPort;
import com.poclll.PoClll.domain.ports.PoClllServicePort;

import java.util.List;

public class PoClllService implements PoClllServicePort {

    private final PoClllRepositoryPort repositoryPort;

    public PoClllService(PoClllRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Poclll savePoc(Poclll poclll) {
        return repositoryPort.save(poclll);
    }

    @Override
    public List<Poclll> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public void deletePoc(Integer id) {
        repositoryPort.delete(id);
    }
}
