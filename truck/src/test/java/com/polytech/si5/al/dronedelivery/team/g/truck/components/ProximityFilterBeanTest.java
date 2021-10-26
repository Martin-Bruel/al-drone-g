package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Address;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageRegistration;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageSelector;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DeliveryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProximityFilterBeanTest {

    @Autowired
    PackageSelector packageSelector;

    @Autowired
    PackageRegistration packageRegistration;

    @Autowired
    PackageFinder packageFinder;

    @Autowired
    DeliveryRepository deliveryRepository;

    List<Delivery> packages;

    @BeforeEach
    @Transactional
    public void setUp() {
        deliveryRepository.deleteAll();
        packages = new ArrayList<>();
    }

    @AfterEach
    public void cleanup() {
        deliveryRepository.deleteAll();
    }

    @Test
    public void allPackageInRangeTest(){

        Delivery delivery1 = new Delivery(new Address(new Position(5,5)));
        Delivery delivery2 = new Delivery(new Address(new Position(0,10)));
        Delivery delivery3 = new Delivery(new Address(new Position(-5,-5)));
        Delivery delivery4 = new Delivery(new Address(new Position(0,-10)));
        Delivery delivery5 = new Delivery(new Address(new Position(-10,0)));
        packages.add(delivery1);
        packages.add(delivery2);
        packages.add(delivery3);
        packages.add(delivery4);
        packages.add(delivery5);
        packageRegistration.registerDelivery(delivery1);
        packageRegistration.registerDelivery(delivery2);
        packageRegistration.registerDelivery(delivery3);
        packageRegistration.registerDelivery(delivery4);
        packageRegistration.registerDelivery(delivery5);

        List<Delivery> deliveries = packageSelector.getDeliverySelected();
        assertEquals(5, deliveries.size());
    }

    @Test
    public void zeroPackageInRangeTest(){

        Delivery delivery1 = new Delivery(new Address(new Position(6,5)));
        Delivery delivery2 = new Delivery(new Address(new Position(-6,5)));
        Delivery delivery3 = new Delivery(new Address(new Position(5,-6)));
        Delivery delivery4 = new Delivery(new Address(new Position(-5,-6)));
        Delivery delivery5 = new Delivery(new Address(new Position(11,0)));
        packages.add(delivery1);
        packages.add(delivery2);
        packages.add(delivery3);
        packages.add(delivery4);
        packages.add(delivery5);
        packageRegistration.registerDelivery(delivery1);
        packageRegistration.registerDelivery(delivery2);
        packageRegistration.registerDelivery(delivery3);
        packageRegistration.registerDelivery(delivery4);
        packageRegistration.registerDelivery(delivery5);

        List<Delivery> deliveries = packageSelector.getDeliverySelected();
        assertEquals(0, deliveries.size());
    }

    @Test
    public void OnePackageInRangeTest(){

        Delivery delivery1 = new Delivery(new Address(new Position(3,-8)));
        Delivery delivery2 = new Delivery(new Address(new Position(-3,5)));
        packages.add(delivery1);
        packages.add(delivery2);
        packageRegistration.registerDelivery(delivery1);
        packageRegistration.registerDelivery(delivery2);

        List<Delivery> deliveries = packageSelector.getDeliverySelected();
        assertEquals(1, deliveries.size());
    }

}