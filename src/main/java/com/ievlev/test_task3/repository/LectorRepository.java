package com.ievlev.test_task3.repository;

import com.ievlev.test_task3.model.Lector;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class LectorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Lector> findByNameLike(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lector> criteriaQuery = criteriaBuilder.createQuery(Lector.class);
        Root<Lector> root = criteriaQuery.from(Lector.class);
        Predicate namePredicate = criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        criteriaQuery.where(namePredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
