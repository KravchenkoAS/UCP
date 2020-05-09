export class Item {
    id_order: number;
    name: string;
    volume: number;
    price: number;
}

export class Backpack {
    bestItems: Array<Item>;
    maxV: number;
    bestPrice: number;
}