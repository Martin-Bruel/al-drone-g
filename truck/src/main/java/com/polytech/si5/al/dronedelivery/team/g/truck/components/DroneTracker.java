package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.dto.PositionDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneStateNotifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneWatcher;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DroneRepository;
import com.polytech.si5.al.dronedelivery.team.g.truck.scheduling.CronTaskRegister;
import com.polytech.si5.al.dronedelivery.team.g.truck.scheduling.SchedulingRunnable;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.net.SocketTimeoutException;
import java.util.HashMap;


@Component("droneTracker")
@EnableAsync
public class DroneTracker implements DroneWatcher {
    private static final Logger logger = LoggerFactory.getLogger(DroneTracker.class);
    @Autowired
    DroneStateNotifier droneStateNotifier;

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    private CronTaskRegister cronTaskRegister;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    DroneService droneService;

    @Autowired
    DroneFinder droneFinder;

    private HashMap<Long,SchedulingRunnable> tasks=new HashMap<>();

    public void doTracking(Long droneId){
        try{
            Drone drone= droneFinder.findDroneById(droneId);
            PositionDto position =droneService.getDronePosition(drone);
            logger.info("Received position of drone "+droneId +": "+position);
        }catch (ResourceAccessException e){

            logger.error(e.getMessage());
            if(e.getCause() instanceof SocketTimeoutException) {
                //
                droneStateNotifier.droneDown(droneId);
                untrack(droneId);
            }
            if(e.getCause() instanceof InterruptedException) {
                //
                droneStateNotifier.droneDown(droneId);
                untrack(droneId);
            }
        }


    }

    public void track(long droneId) {
        logger.info("Tracking drone "+droneId);
        Class[] paramsTypes = new Class[1];
        paramsTypes[0] = Long.class;
        Object[] params= new Object[1];
        params[0]=droneId;
        SchedulingRunnable task = new SchedulingRunnable(applicationContext,"droneTracker", "doTracking", paramsTypes ,params);
        this.tasks.put(droneId,task);
        this.cronTaskRegister.addCronTask(task, "*/5 * * * * *");
    }

    @Override
    public void untrack(long droneId) {
        logger.info("Untracking drone "+droneId);
        SchedulingRunnable task =this.tasks.get(droneId);
        this.cronTaskRegister.removeCronTask(task);
    }
}