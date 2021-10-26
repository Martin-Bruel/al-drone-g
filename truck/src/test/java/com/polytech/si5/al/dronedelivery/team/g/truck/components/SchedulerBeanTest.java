package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class SchedulerBeanTest {

    @Autowired private DroneRegistration droneRegistration;
    @Autowired private PackageRegistration packageRegistration;

    @Mock private PositionProvider positionProvider;
    @Mock private DroneFinder droneFinder;
    @Mock private PackageFinder packageFinder;
    List<Drone> drones;
    List<Delivery> packages;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @InjectMocks
    @Resource
    private SchedulerBean allocationProvider;

    @BeforeEach
    public void setUp() throws Exception {
        drones = new ArrayList<>();
        packages = new ArrayList<>();

        when(positionProvider.getTruckPosition()).thenReturn(new Position(0,0));
    }

    @AfterEach
    public void cleanup() {
        for (Drone d : drones) {
            d = entityManager.merge(d);
            entityManager.remove(d);
        }
        for (Delivery d : packages) {
            d = entityManager.merge(d);
            entityManager.remove(d);
        }
    }

    @Test
    public void schedulingNotEmptyTest() {
        drones.add(new Drone("drone", new ConnectionInterface("localhost","8080")));
        packages.add(new Delivery(new Address(new Position(0,0))));
        when(droneFinder.getAvailableDrones()).thenReturn(drones);
        when(packageFinder.getDeliverablePackages()).thenReturn(packages);
        List<Allocation> allocations = allocationProvider.getAllocations();
        assertThat(allocations).isNotEmpty();
    }

    @Test
    public void schedulingAssignsDronesToPackagesTest() {
        Drone drone = new Drone("drone", new ConnectionInterface("localhost","8080"));
        drones.add(drone);
        droneRegistration.registerDrone(drone);

        Delivery delivery = new Delivery(new Address(new Position(0,0)));
        packages.add(delivery);
        packageRegistration.registerDelivery(delivery);

        when(droneFinder.getAvailableDrones()).thenReturn(drones);
        when(packageFinder.getDeliverablePackages()).thenReturn(packages);

        Allocation allocation = allocationProvider.getAllocations().get(0);
        Drone allocatedDrone = allocation.getDrone();
        Delivery allocatedDelivery = allocation.getDelivery();

        assertTrue(allocatedDrone.getDeliveries().contains(allocatedDelivery));
        assertEquals(allocatedDrone, allocatedDelivery.getDeliveryDrone());
    }

}