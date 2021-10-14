package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.configuration.SchedulerConfiguration;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneStateNotifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneWatcher;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DroneRepository;
import com.polytech.si5.al.dronedelivery.team.g.truck.scheduling.CronTaskRegister;
import com.polytech.si5.al.dronedelivery.team.g.truck.scheduling.SchedulingRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


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

    public void doTracking(Integer droneId){
        logger.info("Log of drone "+droneId);
    }

    public void track(int droneId) {
        Class[] paramsTypes = new Class[1];
        paramsTypes[0] = Integer.class;
        Object[] params= new Object[1];
        params[0]=droneId;
        SchedulingRunnable task = new SchedulingRunnable(applicationContext,"droneTracker", "doTracking", paramsTypes ,params);
        this.cronTaskRegister.addCronTask(task, "* * * * * ?");
    }

    @Override
    public void untrack(int droneId) {

    }
}
