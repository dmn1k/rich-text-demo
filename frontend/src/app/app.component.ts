import {Component, OnInit, ViewChild} from '@angular/core';
import {QuillEditorComponent, QuillModules} from "ngx-quill";
import {HttpClient} from "@angular/common/http";
import {SafeHtml} from "@angular/platform-browser";
import {TodoItem} from "./todoItem";
import {QuillDeltaToHtmlConverter} from "quill-delta-to-html";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  displayContent: SafeHtml = "Kein Inhalt";
  quillModules: QuillModules = {
    toolbar: [
      ['bold', 'italic', 'underline'],
      [{'list': 'bullet'}]
    ]
  };

  @ViewChild('editor', {
    static: true
  }) editor: QuillEditorComponent;

  constructor(private http: HttpClient) {

  }

  ngOnInit(): void {

  }


  sendEditorContent() {
    this.http.post<TodoItem>('http://localhost:7001/jaxrs-demo/api/todos', <TodoItem>{
      description: this.editor.quillEditor.getContents(),
      dueDate: new Date()
    }).subscribe(result => {
      console.dir(result);
      const converter = new QuillDeltaToHtmlConverter(result.description.ops);
      this.displayContent = converter.convert();

      console.log(this.displayContent);
    });
  }
}
