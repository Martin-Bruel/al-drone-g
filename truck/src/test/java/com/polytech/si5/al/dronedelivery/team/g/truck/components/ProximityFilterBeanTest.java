package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Address;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageRegistration;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageSelector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProximityFilterBeanTest {

    @Autowired
    PackageSelector packageSelector;

    @Autowired
    PackageRegistration packageRegistration;

    @Autowired
    PackageFinder packageFinder;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    List<Delivery> packages;

    @BeforeEach
    @Transactional
    public void setUp() {
        packages = new ArrayList<>();
    }

    @AfterEach
    public void cleanup() {

        for (Delivery d : packages) {
            d = entityManager.merge(d);
            entityManager.remove(d);
        }
    }

    @Test
    public void allPackageInRangeTest(){

        Delivery delivery1 = new Delivery(new Address(new Position(5,5)));
        Delivery delivery2 = new Delivery(new Address(new Position(0,9)));
        Delivery delivery3 = new Delivery(new Address(new Position(9,0)));
        packages.add(delivery1);
        packages.add(delivery2);
        packages.add(delivery3);
        packageRegistration.registerDelivery(delivery1);
        packageRegistration.registerDelivery(delivery2);
        packageRegistration.registerDelivery(delivery3);

        List<Delivery> deliveries = packageSelector.getDeliverySelected();
        List<Delivery> deliveriesInDataBase = packageFinder.getDeliverablePackages();
        assertEquals(deliveriesInDataBase.size(), deliveries.size());
    }

    @Test
    public void onePackageInRangeTest(){

        Delivery delivery1 = new Delivery(new Address(new Position(11,11)));
        Delivery delivery2 = new Delivery(new Address(new Position(0,9)));
        Delivery delivery3 = new Delivery(new Address(new Position(-9,0)));
        packages.add(delivery1);
        packages.add(delivery2);
        packages.add(delivery3);
        packageRegistration.registerDelivery(delivery1);
        packageRegistration.registerDelivery(delivery2);
        packageRegistration.registerDelivery(delivery3);

        List<Delivery> deliveries = packageSelector.getDeliverySelected();
        List<Delivery> deliveriesInDataBase = packageFinder.getDeliverablePackages();
        assertEquals(deliveriesInDataBase.size() - 2, deliveries.size());
    }

}