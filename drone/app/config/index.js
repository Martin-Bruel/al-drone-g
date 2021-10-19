const DEFAULT_ENV="dev";
let configuration = {
  server_name:"drone",
  info:{
    id:1
  },
  prod:{
    port: 8084,
    name: process.env.DRONE_NAME,
    database: '' , 
    external: {
      truck : {host:'truck',port:8085}
    }
  },
  dev:{
    port: 8084,
    name: 'alpha',
    database:'',
    external: {
      truck : {host:'localhost',port:8085}
    }
  }
};
function getConfiguration(){
  const ENV= process.env.APP_ENV || DEFAULT_ENV;
  configuration[ENV].port = process.env.PORT || configuration[ENV].port;
  return {service:configuration.server_name,info:configuration.info,context :configuration[ENV], env:ENV};
}

module.exports = { getConfiguration};