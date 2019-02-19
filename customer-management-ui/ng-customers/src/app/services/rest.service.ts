import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from "rxjs";
import {Customer} from "../domain/customer";

@Injectable()
export class RestService {
  base: string = 'http://localhost:8080';
  resource: string = '/';

  constructor(@Inject(HttpClient) private http: HttpClient) { }

  get url() {
    return this.base + this.resource;
  }

  getAll(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.url);
  }

  get (id: number) {
    return this.http.get<Customer>(this.url + '/' + id);
  }

  create (customer: Customer) {
    return this.http.post<Customer>(this.url, customer).subscribe();
  }

  update (id: number, body: any) {
    return this.http.put<Customer>(this.url + '/' + id, body).subscribe();
  }

  delete (id: number) {
    return this.http.delete<Customer>(this.url + "/" + id).subscribe();
  }
}
