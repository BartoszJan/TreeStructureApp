import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NodeService } from './model/node.service';
import { NodesComponent } from './nodes/nodes.component';

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  declarations: [
    AppComponent,
    NodesComponent,
  ],
  providers: [NodeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
