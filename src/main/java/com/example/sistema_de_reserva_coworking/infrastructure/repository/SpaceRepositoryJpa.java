package com.example.sistema_de_reserva_coworking.infrastructure.repository;

import com.example.sistema_de_reserva_coworking.application.mapper.SpaceMapper;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.SpaceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpaceRepositoryJpa implements SpaceRepository {

    private final JpaSpaceRepository jpaSpaceRepository;

    @Override
    public List<Space> findAll() {
       return jpaSpaceRepository.findAll().stream().map(SpaceMapper::mapSpaceEntityToSpace).toList();
    }

    @Override
    public Space save(Space space) {

        SpaceEntity entity = SpaceMapper.mapSpaceToSpaceEntity(space);

        return SpaceMapper.mapSpaceEntityToSpace(jpaSpaceRepository.save(entity));
    }

    @Override
    public Optional<Space> findById(Long id) {

        return jpaSpaceRepository.findById(id).map(SpaceMapper::mapSpaceEntityToSpace);
    }

    @Override
    public void deleteById(Long id) {

        jpaSpaceRepository.deleteById(id);
    }

    @Override
    public Optional<Space> findByName(String name) {

        return jpaSpaceRepository.findByName(name).map(SpaceMapper::mapSpaceEntityToSpace);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaSpaceRepository.existsByName(name);
    }
}
