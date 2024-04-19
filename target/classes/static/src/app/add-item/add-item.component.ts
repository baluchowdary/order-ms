import { Component, OnInit } from '@angular/core';
import { Stock } from '../stock';
import { OrderService } from '../order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit{

  stock: Stock = new Stock();

  constructor(private orderservice : OrderService, private router:Router){}

  ngOnInit(): void {

  }

  addItems(){
    console.log("AddItemComponent.addItems()");
    this.orderservice.addItems(this.stock).subscribe(data =>{
      this.stock =data;
    });
    this.goToOrderList();
  }

  goToOrderList(){
    console.log("AddItemComponent.goToOrderList()");
    this.router.navigate(["/orders"]);
  }

  onSubmit(){
    console.log("AddItemComponent.onSubmit()");
    //console.log(this.stock);
    this.addItems();
  }

}
