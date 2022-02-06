export enum DroneStatus {
    READY = 3,
    FLYINGTODELIVERY = 1,
    FLYINTOTRUCK = 4,
    LOST = 5
}

export enum DeliveryStatus {
    PENDING = 'PENDING'
}

export interface Position {
    latitude: number,
    longitude: number
}

export interface Delivery {
    id: number,
    position: Position,
    deliveryStatus: DeliveryStatus
}

export interface DroneType {
    id: number,
    name: string,
    status: DroneStatus,
    deliveries: Array<Delivery>,
    capacity: number,
    position: Position
}


export interface Truck {
    available: boolean,
    drones:Array<DroneType>,
    position: Position | undefined
}