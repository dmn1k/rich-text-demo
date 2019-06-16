import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {QuillModule} from "ngx-quill";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    QuillModule.forRoot({
      theme: 'snow'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
