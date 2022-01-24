const fs = require('fs');

class Registry{

    constructor(name){
        this.name = name;
        this.filePath = `${__dirname}/../mocks/${this.name.toLowerCase()}.mocks.json`
        this.items = [];
        //this.load()
    }

    load(){
        try {
            this.items = JSON.parse(fs.readFileSync(this.filePath, 'utf8'));
        } catch (err) {
            if (err.message === 'Unexpected end of JSON input') console.log('Warning : ' + this.filePath +' has wrong JSON format');
        }
    }

    save(){
        try {
            fs.writeFileSync(this.filePath, JSON.stringify(this.items, null, 2), 'utf8');
        } catch (err) {
            console.log('Error while trying to save ' + this.name);
        }
    }

    get(){
        return this.items;
    }

    clean(){
        this.items = [];
        //this.save();
    }

    find(filter){
        const item = this.items.find((i) => i[Object.keys(filter)[0]] === Object.values(filter)[0]);
        if (!item) throw new NotFoundError('Cannot get '+ this.name + 'item with ' + filter +' : not found');
        return item;
    }

    create(obj){
        this.items.push(obj);
        //this.save();
        return obj;
    }

    update(oldObj, newObj){
        const prevObjIndex = this.items.findIndex((item) => item === oldObj);
        if (prevObjIndex === -1) throw new NotFoundError('Cannot update '+ this.name + 'item ' + oldObj +' : not found');
        const updatedItem = { ...this.items[prevObjIndex], ...newObj };
        this.items[prevObjIndex] = updatedItem;
        //this.save();
        return updatedItem;
    }

    delete(obj){
        const objIndex = this.items.findIndex((item) => item === obj);
        if (objIndex === -1) throw new NotFoundError('Cannot delete '+ this.name + 'item ' + oldObj +' : not found');
        this.items = this.items.filter((item) => item !== obj);
        //this.save();
    }
}

module.exports = {
    Registry
}