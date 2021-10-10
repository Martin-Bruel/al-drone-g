const DEFAULT_ENV="dev";
let configuration = {
  server_name:"drone",
  prod:{
    port: 8080,
    database: '' , 
    external: {
    }
  },
  dev:{
    port: 8080,
    database:'',
    external: {
    }
  }
};
function getConfiguration(){
  const ENV= process.env.APP_ENV || DEFAULT_ENV;
  configuration[ENV].port = process.env.PORT || configuration[ENV].port;
  configuration[ENV].database = process.env.DATABASE_URL || configuration[ENV].database;
  return {service:configuration.server_name,context :configuration[ENV], env:ENV};
}

module.exports = { getConfiguration};