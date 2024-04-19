import { Component, OnInit } from '@angular/core';
import { Order } from '../order';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit{

  orders: Order[] = [];

  constructor(private orderService: OrderService){ }

  ngOnInit(): void {
    console.log("OrderListComponent.ngOnInit()");
    this.getOrders();
  }

  private getOrders(){
    console.log("OrderListComponent.getOrders()");
    this.orderService.getOrderList().subscribe(data => {
      this.orders =data;
    });
  }

  deleteOrder(id: number){
    this.orderService.deleteOrder(id).subscribe(data =>{
      this.getOrders();
    })
  }

}
