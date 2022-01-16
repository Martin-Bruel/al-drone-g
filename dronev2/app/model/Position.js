class Position{

    constructor(lat, lon){
        this.lat = lat;
        this.lon = lon;
    }

    getLon(){
        return this.lon;
    }

    getLat(){
        return this.lat;
    }

    equals(position){
        var precision = 0.00001;
        return (Math.abs(this.lat - position.lat) <= precision && Math.abs(this.lon - position.lon) <= precision);
    }

    distance(position){
        var a = this.lat - position.lat;
        var b = this.lon - position.lon;
        return Math.sqrt( a*a + b*b );
    }

    toString(){
        return '('+Math.round(this.lat * 1000) / 1000 +','+Math.round(this.lon * 1000) / 1000+')';
    }
}

module.exports = {
    Position
}