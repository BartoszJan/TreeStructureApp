import { Component, OnInit } from '@angular/core';

import { Node } from 'src/app/model/node';
import { NodeService } from 'src/app/model/node.service';

@Component({
  selector: 'app-nodes',
  templateUrl: './nodes.component.html',
  styleUrls: ['./nodes.component.less']
})
export class NodesComponent implements OnInit {

  nodeList: Node[];
  dataAvailable: boolean;
  errorMessage: String;
  selectedTreeNode: Node;

  constructor(private nodeService: NodeService) {
    this.selectedTreeNode = null;
    this.dataAvailable = false;
    this.nodeList = null; }

  ngOnInit() {
    this.getNodeList(); }

  getNodeList(): void {
    this.dataAvailable = true;

    this.nodeService.getAllNodes()
      .subscribe(
        nodeList => {
          if(nodeList.length > 0) {
            this.nodeList = nodeList;
          } else {
            this.dataAvailable = false;
          }
        },
        error =>  this.errorMessage = <any>error );
  }

  selectNode( node: Node ) : void {
  this.selectedTreeNode = node;
  console.log( "Selected node with id:", node.id );
  }
}
