package com.polytech.si5.al.dronedelivery.team.g.truck.constants;

public class Api {
    public static final String DEFAULT_ENV = "dev";
    public static final String ENV = System.getenv("APP_ENV") != null ? System.getenv("APP_ENV") : DEFAULT_ENV;
    public static final int TIMEOUT=3000; //m
    public static final int ACCEPTABLE_DRONE_TIMEOUT=5000;
                //////////- Drone APIs-//////////
    public static final String DRONE_API_BASE_URL="drone-api";

              //////////- Warehouse APIs-//////////
    public static final String WAREHOUSE_API_HOST = ENV.equals("prod") ? "warehouse" : "localhost";
    public static final String WAREHOUSE_API_PORT ="8086";
    public static final String WAREHOUSE_API_BASE_URL ="warehouse-api";

}
