const DEFAULT_ENV="dev";
let configuration = {
    server_name: "drone",
    info: {
        id: 1,
        speed: 0.5,
        connected: true
    },
    prod: {
        host: process.env.DRONE_HOST,
        port: process.env.DRONE_PORT,
        name: process.env.DRONE_NAME,
        capacity: process.env.DRONE_CAPACITY,
        external: {
            truck: {host: 'truck', port: 8085},
            map: {host: 'map', port: 8081}
        }
    },
    dev: {
        host: 'localhost',
        port: process.env.DRONE_PORT || 8087,
        name: 'alpha',
        capacity: 1,
        external: {
            truck: {host: 'localhost', port: 8085},
            map: {host: 'localhost', port: 8081}
        }
    },
    multiDrone: {
        host: 'localhost',
        port: process.env.DRONE_PORT,
        name: process.env.DRONE_NAME,
        capacity: 1,
        external: {
            truck: {host: 'localhost', port: 8085},
            map: {host: 'localhost', port: 8081}
        }
    }
};
function getConfiguration(){
  const ENV= process.env.APP_ENV || DEFAULT_ENV;
  configuration[ENV].port = process.env.PORT || configuration[ENV].port;
  return {service:configuration.server_name,info:configuration.info,context :configuration[ENV], env:ENV};
}

module.exports = { getConfiguration};
