export class Item {
    id_order: number;
    name: string;
    weight: number;
    price: number;
}

export class Backpack {
    bestItems: Array<Item>;
    maxW: number;
    bestPrice: number;
}