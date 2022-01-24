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
        var a = this.latitude - position.latitude;
        var b = this.longitude - position.longitude;
        return Math.sqrt( a*a + b*b );
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