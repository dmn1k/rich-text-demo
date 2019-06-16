import {Delta} from 'quill';

export interface TodoItem {
  description: Delta,
  dueDate: Date
}
