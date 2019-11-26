import { SegmentCreate } from '../segment/segment';

export class Dictionary {
    id_dictionary: number;
    way: number;
    sequence: number;
    id_route: number;
    id_segment: number;

    init (id_dictionary: number, way: number, sequence: number, id_route: number, id_segment: number) {
        this.id_dictionary = id_dictionary;
        this.way = way;
        this.sequence = sequence;
        this.id_route = id_route;
        this.id_segment = id_segment;
    }
}

export class WayDTO {
    way: number;
    id_route: number;
    id_segment: Array<number>;
    time: number;
    distance: number;
    price: number;

    init (data: any) {
        this.way = data.way;
        this.id_route = data.id_route;
        this.id_segment = data.id_segment;
        this.time = data.time;
        this.distance = data.distance;
        this.price = this.price;
    }
}