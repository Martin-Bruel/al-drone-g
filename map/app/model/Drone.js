class Drone{

    constructor(id, name, status, position, connectionInterface){
        this.id = id;
        this.name = name;
        this.status = status;
        this.position = position;
        this.connectionInterface = connectionInterface;
    }
}

module.exports = {
    Drone
}