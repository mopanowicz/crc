import { ActivatedRoute } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Item } from '../item';
import { ItemsService } from '../items.service';
import { StringUtils } from '../string-utils';

@Component({
  selector: 'app-item-update',
  templateUrl: './item-update.component.html',
  styleUrls: ['./item-update.component.css']
})
export class ItemUpdateComponent implements OnInit {

  errorMessage?: string;

  item?: Item

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private itemService: ItemsService
    ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.itemService.get(id).subscribe(v => this.item = v);
  }

  isValid(): boolean {
    return this.item != undefined && StringUtils.hasText(this.item!.name);
  }

  onSubmit(): void {
    this.errorMessage = undefined;
    if (!this.isValid()) {
      return;
    }
    this.itemService.update(this.item!).subscribe({
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
