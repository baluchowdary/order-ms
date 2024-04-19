import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderListComponent } from './order-list/order-list.component';
import { CreateOrderComponent } from './create-order/create-order.component';
import { AddItemComponent } from './add-item/add-item.component';

const routes: Routes = [
  {path: "", redirectTo:"orders", pathMatch:"full"},
  {path: "orders", component: OrderListComponent},
  {path: "createOrders", component: CreateOrderComponent},
  {path: "addItems", component: AddItemComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
