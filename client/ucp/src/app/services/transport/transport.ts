export class Transport {
    id_transport: number;
    fuel: string;
    type_delivery: string;
    name: string;
    speed: number;
    max_volume: number;
    max_weight: number;
    price: number;
    coefficient: number;
    fuel_consumption: number;

    init(trnsport: any){
        this.fuel = trnsport.fuel;
        this.name = trnsport.name;
        this.type_delivery = trnsport.type_delivery;
        this.speed = trnsport.speed;
        this.max_volume = trnsport.max_volume;
        this.max_weight = trnsport.max_weight;
        this.price = trnsport.price;
        this.coefficient = trnsport.coefficient;
        this.fuel_consumption = trnsport.fuel_consumption;
      }

}