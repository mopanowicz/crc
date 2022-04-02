import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ItemsComponent } from './items/items.component';
import { ItemsCreateComponent } from './items-create/items-create.component';
import { ItemsUpdateComponent } from './items-update/items-update.component';

const routes: Routes = [
  { path: 'items', component: ItemsComponent },
  { path: 'items-create', component: ItemsCreateComponent },
  { path: 'items-update/:id', component: ItemsUpdateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
