import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from './order';
import { Customerorder } from './customerorder';

@Injectable({
  providedIn: 'root'
})
export class OrderService {


  private getOrderbBseURL = "http://localhost:8081/api/getOrderDetails";

  private createOrderBaseURL = "http://localhost:8081/api/createOrders";

  private deleteOrderBaseURL = "http://localhost:8081/api";

  constructor(private httpClient: HttpClient) { }

  getOrderList(): Observable<Order[]>{
    console.log("OrderService.getOrderList()");
    return this.httpClient.get<Order[]>(`${this.getOrderbBseURL}`);
  }

  createOrder(customerorder: Customerorder): Observable<any>{
    console.log("OrderService.createOrder()");
    return this.httpClient.post(`${this.createOrderBaseURL}`, customerorder);
  }

  deleteOrder(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.deleteOrderBaseURL}/${id}`);
  }
}
