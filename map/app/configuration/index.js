const DEFAULT_ENV="dev";
let configuration = {
    server_name: "map",
    info: {
        radius: 3
    },
    prod: {
        host: process.env.MAP_HOST || "map",
        port: process.env.MAP_PORT || 8081,
        wsport: process.env.MAP_WSPORT || 3000
    },
    dev: {
        host: 'localhost',
        port: process.env.MAP_PORT || 8081,
        wsport: process.env.MAP_WSPORT || 3000
    }
};
function getConfiguration(){
  const ENV= process.env.APP_ENV || DEFAULT_ENV;
  configuration[ENV].port = process.env.PORT || configuration[ENV].port;
  configuration[ENV].wsport = process.env.WSPORT || configuration[ENV].wsport;
  return {service:configuration.server_name,info:configuration.info,context :configuration[ENV], env:ENV};
}

module.exports = { getConfiguration};
