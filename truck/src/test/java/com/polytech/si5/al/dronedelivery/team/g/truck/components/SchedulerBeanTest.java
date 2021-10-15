package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.ConnectionInterface;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PositionProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class SchedulerBeanTest {

    @Mock private PositionProvider positionProvider;
    @Mock private DroneFinder droneFinder;
    @Mock private PackageFinder packageFinder;
    List<Drone> drones;
    List<Delivery> packages;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }


    @InjectMocks
    @Resource
//
//    @Autowired
    private SchedulerBean allocationProvider;

    @BeforeEach
    public void setUp() throws Exception {
        drones = new ArrayList<>();
        packages = new ArrayList<>();

        when(positionProvider.getTruckPosition()).thenReturn(new Position(0,0));
    }

    @Test
    public void schedulingNotEmptyTest() {
        drones.add(new Drone("drone", new ConnectionInterface("localhost","8080")));
        packages.add(new Delivery());
        when(droneFinder.getAvailableDrones()).thenReturn(drones);
        when(packageFinder.getDeliverablePackages()).thenReturn(packages);
        assertThat(allocationProvider.getAllocations()).isNotEmpty();
    }

}