export class OrderCreate {
    id_order: number;
    type: string;
    name: string;
    length: number;
    width: number;
    height: number;
    weight: number;
    amount: number;
    startPoint: string;
    endPoint: string;
    date: Date;
    isDocuments: boolean;
    isContainer: boolean;

    create(event: { id_order: number; type: string; name: string; length: number; width: number;
        height: number; weight: number; amount: number; startPoint: string; endPoint: string; date: Date;
        isContainer: boolean; isDocuments: boolean;}) {
        return { id_order: event.id_order, type: event.type, name: event.name, length: event.length, width: event.width
            , height: event.height, weight: event.weight, amount: event.amount, startPoint: event.startPoint
            , endPoint: event.endPoint, date: event.date, isContainer: event.type, isDocuments: event.type };
      }

      init(order: any){
        this.id_order = order.id_order;
        this.type = order.type;
        this.name = order.name;
        this.length = order.length;
        this.width = order.width;
        this.height = order.height;
        this.weight = order.weight;
        this.amount = order.amount;
        this.startPoint = order.startPoint;
        this.endPoint = order.endPoint;
        this.date = order.date;
        this.isContainer = order.isContainer;
        this.isDocuments = order.isDocuments; 
      }

      getStartPoint(order: OrderCreate){
          return order.startPoint;
      }
}

export class Order {
    id_order: number;
    name: string;
    date: Date;
    status: string;
    date_dispatch: Date;
    price: number;
    isDocuments: boolean;
    isContainer: boolean;
}