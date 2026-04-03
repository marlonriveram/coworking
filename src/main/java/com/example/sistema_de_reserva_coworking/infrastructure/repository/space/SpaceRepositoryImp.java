package com.example.sistema_de_reserva_coworking.infrastructure.repository.space;

import com.example.sistema_de_reserva_coworking.application.mapper.SpaceMapper;
import com.example.sistema_de_reserva_coworking.domain.model.Space;
import com.example.sistema_de_reserva_coworking.domain.repository.SpaceRepository;
import com.example.sistema_de_reserva_coworking.infrastructure.persistence.entity.SpaceEntity;
import com.example.sistema_de_reserva_coworking.infrastructure.repository.projections.SpaceCapacity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpaceRepositoryImp implements SpaceRepository {

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
    public List<Space> saveAll(List<Space> spaces) {

        List<SpaceEntity> entities = spaces.stream()
                .map(SpaceMapper::mapSpaceToSpaceEntity)
                .toList();

        return jpaSpaceRepository.saveAll(entities).stream().map(SpaceMapper::mapSpaceEntityToSpace).toList();
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
    public boolean existsById(Long id) {
        return jpaSpaceRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaSpaceRepository.existsByName(name);
    }

    @Override
    public Space getReferenceById(Long id) {
        SpaceEntity response = jpaSpaceRepository.getReferenceById(id);
        return SpaceMapper.mapSpaceEntityToSpace(response);
    }


    //Projection, para traer solo una columna en especifico
    @Override
    public Optional<SpaceCapacity> findMaxCapacityById(Long id) {
        return jpaSpaceRepository.findMaxCapacityById(id);
    }
}
