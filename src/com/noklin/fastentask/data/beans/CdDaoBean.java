package com.noklin.fastentask.data.beans;

import com.noklin.fastentask.data.entities.CdEntity;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;


@Stateless(name = "CdDaoEJB")
public class CdDaoBean {
    @PersistenceContext
    private EntityManager em;

    public CdDaoBean() {
    }

    public void addOrUpdate(CdEntity entity){
        em.merge(entity);
    }

    public List<CdEntity> getAllCds() {
        CriteriaQuery<CdEntity> criteria = em.getCriteriaBuilder().createQuery(CdEntity.class);
        criteria.select(criteria.from(CdEntity.class));
        return em.createQuery(criteria).getResultList();
    }

    public CdEntity find(String id) {
        return em.find(CdEntity.class , id);
    }

}
