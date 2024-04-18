import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from './order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {


  private baseURL = "http://localhost:8081/api/getOrderDetails";
  constructor(private httpClient: HttpClient) { }

  getOrderList(): Observable<Order[]>{
    return this.httpClient.get<Order[]>(`${this.baseURL}`)
  }
}
