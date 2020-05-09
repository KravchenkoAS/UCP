export class Transport {
    id_transport: number;
    fuel: string;
    type_delivery: string;
    name: string;
    speed: number;
    max_volume: number;
    max_weight: number;
    max_width: number
    price: number;
    coefficient: number;
    fuel_consumption: number;
    crewCost: number;

    init(transport: any){
        this.fuel = transport.fuel;
        this.name = transport.name;
        this.type_delivery = transport.type_delivery;
        this.speed = transport.speed;
        this.max_volume = transport.max_volume;
        this.max_weight = transport.max_weight;
        this.price = transport.price;
        this.coefficient = transport.coefficient;
        this.fuel_consumption = transport.fuel_consumption;
        this.max_width = transport.max_width;
        this.crewCost = transport.crewCost;
      }

}