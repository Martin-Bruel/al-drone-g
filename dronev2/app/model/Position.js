const {deg2rad} = require('../utils/MathUtil');
class Position{

    constructor(latitude, longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    getLon(){
        return this.longitude;
    }

    getLat(){
        return this.latitude;
    }

    equals(position){
        var precision = 0.00001;
        return (Math.abs(this.latitude - position.latitude) <= precision && Math.abs(this.longitude - position.longitude) <= precision);
    }

    distance(position){

        var lat1 = this.latitude;
        var lat2 = position.latitude;
        var lon1 = this.longitude;
        var lon2 = position.longitude;
        
        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(lat2-lat1);
        var dLon = deg2rad(lon2-lon1); 
        var a = 
        Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
        Math.sin(dLon/2) * Math.sin(dLon/2)
        ; 
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
        var d = R * c; // Distance in km
        return d;
    }

    toString(){
        return '('+Math.round(this.latitude * 1000) / 1000 +','+Math.round(this.longitude * 1000) / 1000+')';
    }

    format(){
        return { "latitude": this.latitude, "longitude": this.longitude };
    }
}

module.exports = {
    Position
}