export class SegmentCreate {
    id_segment: number;
    id_order: number;
    type_delivery: string;
    time: number;
    id_transport: number;
    amount_transport: number;
    distance: number;
    price: number;
    startPoint: string;
    endPoint: string;

    init(segment: SegmentCreate) {
        this.id_segment = segment.id_segment;
        this.id_order = segment.id_order;
        this.type_delivery = segment.type_delivery;
        this.time = segment.time;
        this.id_transport = segment.id_transport;
        this.amount_transport = segment.amount_transport;
        this.price = segment.price;
        this.startPoint = segment.startPoint;
        this.endPoint = segment.endPoint;
        this.distance = segment.distance;
      }
}

export class Segment {
    id_segment: number;
    time: number;
    id_transport: number;
    distance: number;
    price: number;
    startPoint: string;
    endPoint: string;
    amount_transport: number;
}

export class SegmentDetailsDTO {
    id_segment: number;
    id_order: number;
    type_delivery: string;
    time: number;
    transport: string;
    amount_transport: number;
    distance: number;
    price: number;
    startPoint: string;
    endPoint: string;
}