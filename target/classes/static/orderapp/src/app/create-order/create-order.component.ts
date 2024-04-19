import { Component, OnInit } from '@angular/core';
import { Order } from '../order';
import { OrderService } from '../order.service';
import { Router } from '@angular/router';
import { Customerorder } from '../customerorder';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  //order: Order = new Order();
  customerorder: Customerorder = new Customerorder();

  constructor(private orderservice : OrderService, private router:Router){}

  ngOnInit(): void {
    
  }

  createOrder(){
    console.log("CreateOrderComponent.createOrder()");
    this.orderservice.createOrder(this.customerorder).subscribe(data => {
      this.customerorder = data;
    });
    //console.log(this.customerorder);
      this.goToOrderList();
  }

  goToOrderList(){
    console.log("CreateOrderComponent.goToOrderList()");
    this.router.navigate(["/orders"]);
  }

  onSubmit(){
    console.log("CreateOrderComponent.onSubmit()");
    //console.log(this.customerorder);
    this.createOrder();
  }

}
