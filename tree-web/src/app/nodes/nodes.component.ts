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

  nodeList: Node[];
  dataAvailable: boolean;
  valueFormDisabled: boolean;
  errorMessage: String;
  selectedTreeNode: Node;

  constructor(private nodeService: NodeService) {
    this.selectedTreeNode = null;
    this.dataAvailable = false;
    this.nodeList = null;
    this.valueFormDisabled = false;
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
          } else {
            this.dataAvailable = false;
          }
        },
        error =>  this.errorMessage = <any>error );
  }

  public selectNode( node: Node ) : void {
    this.selectedTreeNode = node;
    console.log( "Selected node with id:", node.id );
  }

  setNodeValue( valueForm : NgForm) {

  }

  public isNodeSelected() : boolean {
    return this.selectedTreeNode === null;
  }

  public disableValueButton() : boolean {
    return this.isNodeSelected() || this.isLeaf(this.selectedTreeNode);
  }

  public disableDeleteButton() : boolean {
    return this.isNodeSelected() || this.isRoot(this.selectedTreeNode);
  }

  public isLeaf( node : Node ) : boolean {
    return node.children === null || node.children.length === 0;
  }

  public isRoot( node : Node ) : boolean {
    return node === this.nodeList[0];
  }

  public openForm() : void {
    this.valueFormDisabled = true;
  }

}
