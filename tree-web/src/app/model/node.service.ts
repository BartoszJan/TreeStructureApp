import { HttpClient, HttpErrorResponse, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError as observableThrowError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { Node } from './node';

const HTTP_OPTIONS = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class NodeService {

  private baseUrl : string;
  private httpOptions;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/';
    this.httpOptions = HTTP_OPTIONS;
  }

  public getAllNodes() : Observable<Node[]> {
    return this.http
      .get<Node[]>(this.baseUrl +  "nodes")
      .pipe(map(data => data), catchError(this.handleError));
   }

  public createNode(parentNode : Node) : Observable<Node> {
    var node = new Node();
    node.parentId = parentNode.parentId;
    node.children = parentNode.children;
    node.value = 0;
    console.log(node);

    return this.http
     .post(this.baseUrl +  "node", node, this.httpOptions);
  }

  private handleError(res: HttpErrorResponse | any) {
    console.error(res.error || res.body.error);
    return observableThrowError(res.error || 'Server error');
  }
}
