package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class PackageRegistryBean implements PackageFinder, PackageRegistration {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(PackageRegistryBean.class);


    @Override
    public List<Delivery> getDeliverablePackages() {
        return (List<Delivery>) entityManager.createQuery(
                "SELECT e FROM Delivery e", Delivery.class).getResultList();
    }

    @Override
    public Delivery getPackageByPackageId(Long packageId) {
       logger.info("find delivery for id " + packageId);
        Delivery delivery = entityManager.find(Delivery.class, packageId);
        if(delivery == null) throw new IllegalArgumentException("Drone " + packageId + " not found...");
        return delivery;
    }

    @Override
    public List<Delivery> getPackagesByDroneId(Long droneId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Delivery> cr = cb.createQuery(Delivery.class);
        Root<Delivery> root = cr.from(Delivery.class);
        root.join("deliveryDrone").get("id");
        cr.select(root).where(cb.gt(root.get("id"),droneId));
        TypedQuery<Delivery> query = entityManager.createQuery(cr);
        try {
            return query.getResultList();

        } catch (NoResultException nre) {
            throw new NoResultException(nre.getMessage());
        }
    }

    @Override
    @Transactional
    public void registerDelivery(Delivery delivery) {
        entityManager.persist(delivery);
    }
}
