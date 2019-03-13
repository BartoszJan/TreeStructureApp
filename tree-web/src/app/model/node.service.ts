import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError as observableThrowError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { Node } from './node';

@Injectable({
  providedIn: 'root'
})
export class NodeService {

  private baseUrl : string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/';
  }

  getAllNodes() {
    return this.http
      .get<Node[]>(this.baseUrl +  "nodes")
      .pipe(map(data => data), catchError(this.handleError));
   }

  private handleError(res: HttpErrorResponse | any) {
    console.error(res.error || res.body.error);
    return observableThrowError(res.error || 'Server error');
  }
}
