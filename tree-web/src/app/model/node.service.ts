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

  public createNode(node : Node) : Observable<any> {
    var newNode = new Node();
    newNode.parentId = node.id;
    newNode.children = null;
    newNode.value = 0;

    return this.http.post(this.baseUrl +  "node", newNode, this.httpOptions);
  }

  public copyNode(node : Node) : Observable<any> {
      return this.http.post(this.baseUrl +  "copy_node", node, this.httpOptions);
    }

  public saveNode(node : Node) : Observable<any> {
    return this.http.put(this.baseUrl +  "node" + "/" + node.id, node, this.httpOptions);
  }

  deleteNode(node : Node): Observable<any> {
      return this.http.delete(this.baseUrl +  "node" + "/" + node.id, { responseType: 'text' });
  }

  private handleError(res: HttpErrorResponse | any) {
    console.error(res.error || res.body.error);
    return observableThrowError(res.error || 'Server error');
  }
}
