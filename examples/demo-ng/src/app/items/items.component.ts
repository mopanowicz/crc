import { Component, OnInit } from '@angular/core';

import { ItemsService } from '../items.service';
import { Item } from '../item';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {

  items: Item[] = [];

  constructor(private itemsService: ItemsService) { }

  ngOnInit(): void {
    this.itemsService.getAll().subscribe((data: Item[])=>{
      this.items = data;
    })
  }

  deleteItem(id: number) {
    if (confirm("Are you sure you want to delete the item with id="+ id)) {
      this.itemsService.delete(id).subscribe(res => {
        this.items = this.items.filter(item => item.id !== id);
        console.log(`Item id={id} deleted`);
      })
    }
  }
}
