import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { Node } from 'src/app/model/node';
import { NodeService } from 'src/app/model/node.service';

@Component({
  selector: 'app-nodes',
  templateUrl: './nodes.component.html',
  styleUrls: ['./nodes.component.less']
})
export class NodesComponent implements OnInit {

  nodeList : Node[];
  dataAvailable : boolean;
  selectedTreeNode : Node;
  value : number;

  constructor(private nodeService: NodeService) {
    this.selectedTreeNode = null;
    this.dataAvailable = false;
    this.nodeList = null;
    this.value = 0;
  }

  ngOnInit() {
    this.getNodeList();
  }

  public getNodeList(): void {
    this.dataAvailable = true;

    this.nodeService.getAllNodes()
      .subscribe(
        nodeList => {
          if(nodeList.length > 0) {
            this.nodeList = nodeList;
            console.log(nodeList);
            this. selectedTreeNode = null;
          } else {
            this.dataAvailable = false;
          }
        }, error => console.log(error) );
  }

  public addNode() : void {
    this.nodeService.createNode(this.selectedTreeNode)
      .subscribe(data => {
          console.log(data);
          this.getNodeList();
          this. selectedTreeNode = null;
        }, error => console.log(error) );
  }

  public updateNode() {
    if(Number.isInteger(this.value)) {
      this.selectedTreeNode.value = this.value;
      this.nodeService.saveNode(this.selectedTreeNode)
        .subscribe(data => {
            console.log(data);
            this.getNodeList();
          }, error => console.log(error) );
    }
  }

  public deleteNode() {
    this.nodeService.deleteNode(this.selectedTreeNode)
      .subscribe(data => {
          console.log(data);
          this.getNodeList();
        }, error => console.log(error) );
  }

  public copyNode() {
    this.nodeService.copyNode(this.selectedTreeNode)
      .subscribe(data => {
          console.log(data);
          this.getNodeList();
        }, error => console.log(error) );
  }

  public selectNode( node: Node ) : void {
    this.selectedTreeNode = node;
    this.value = node.value;
    console.log( "Selected node with id:", node.id );
  }

  public isNodeSelected() : boolean {
    return this.selectedTreeNode === null;
  }

  public disableValueForm() : boolean {
    return this.isNodeSelected() || this.isLeaf(this.selectedTreeNode);
  }

  public disableDeleteCopyButton() : boolean {
    return this.isNodeSelected() || this.isRoot(this.selectedTreeNode);
  }

  public isLeaf( node : Node ) : boolean {
    return (node.children === null || node.children.length === 0) && !this.isRoot(node);
  }

  public isRoot( node : Node ) : boolean {
    return node === this.nodeList[0];
  }

  calculateClasses( node : Node ) : string {
    if(node.parentId === null)
      return "node__root";
    else if(node.children.length > 0)
      return "node__value";
    else
      return "node__leaf";
  }
}
