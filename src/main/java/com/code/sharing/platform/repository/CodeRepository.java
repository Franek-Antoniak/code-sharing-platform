package com.code.sharing.platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.code.sharing.platform.model.CodeHolder;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<CodeHolder, Long> {
    Optional<List<CodeHolder>> findTop10ByRestrictionOrderByIdDesc(boolean restriction);

    Optional<CodeHolder> findByUniqueId(String uniqueId);
}
