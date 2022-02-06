import { InjectionKey } from 'vue';
import { DroneStatus, DroneType, Position } from '@/types/types';
import { createStore, Store } from 'vuex'

export interface State {
    truckPosition: Position,
    drones: Array<DroneType>
}

export const key: InjectionKey<Store<State>> = Symbol();

export const store = createStore<State>({
    state:{
        truckPosition: {
            latitude: 43.617226,
            longitude: 7.075738
        },
        drones: []
    },
    getters:{
        truck: (state) => {
            return state.truckPosition;
        },
        truckPositionAsArray: (state) => {
            return [state.truckPosition.longitude, state.truckPosition.latitude];
        },
        drones: (state) => {
            return state.drones;
        },
        droneById: (state) => (id: number) => {
            return state.drones.find(drone => drone.id === id);
        },
        dronePositionAsArray: (state, getters) => (id: number) => {
            const drone: DroneType = getters.droneById(id);
            return [drone.position.longitude, drone.position.latitude];
        }
    },
    mutations:{
        updateDrone(state, data: {position: Position, id: number, name: string, status: number}){

            if(state.drones.find(drone => drone.id === data.id) === undefined){
                state.drones.push({
                    id: data.id,
                    name: data.name,
                    status: (<any>DroneStatus)[data.status],
                    deliveries: [],
                    capacity: 1,
                    position: data.position
                })
            }
            else{
                const drone = state.drones.find(drone => drone.id === data.id);
                if(drone !== undefined){
                    drone.position = data.position;
                    drone.status = data.status;
                }
            }
        }
    },
    actions:{}
})