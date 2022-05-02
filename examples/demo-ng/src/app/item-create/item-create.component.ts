import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Item } from '../item';
import { ItemsService } from '../items.service';
import { StringUtils } from '../string-utils';

@Component({
  selector: 'app-item-create',
  templateUrl: './item-create.component.html',
  styleUrls: ['./item-create.component.css']
})
export class ItemCreateComponent {

  errorMessage?: string;

  item: Item = {
    name: ''
  }

  constructor(
    private router: Router,
    private itemService: ItemsService
    ) { }

  isValid(): boolean {
    return this.item != undefined && StringUtils.hasText(this.item!.name);
  }

  onSubmit(): void {
    this.errorMessage = undefined;
    if (!this.isValid()) {
      return;
    }
    this.itemService.create(this.item!).subscribe({
      next: (v) => console.log(v),
      error: (e) => {
        console.error(e)
        this.errorMessage = e.message
      },
      complete: () => {
        console.info('complete')
        this.router.navigate(['items'])
      }
    })
  }

  onCancel(): void {
    this.router.navigate(['items'])
  }
}
