import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SignupComponent } from './components/signup/signup.component';    // <<<---
import { LoginComponent } from './components/login/login.component';       // <<<--
import { HomeComponent } from './components/home/home.component';          // <<<--
import { UserComponent } from './components/user/user.component';          // <<<--
import { AdminComponent } from './components/admin/admin.component';       // <<<--
import { DeliveryComponent } from "./components/delivery/delivery.component";
import { BidClientComponent } from "./components/bid-client/bid-client.component";
import { SettingComponent } from "./components/setting/setting.component";
import { AddDeliveryComponent } from "./components/delivery/add-delivery/add-delivery.component";
import { CreateRouteComponent } from './components/create-route/create-route.component';
import { BidClientDetailsComponent } from './components/bid-client/bid-client-details/bid-client-details.component';
import { SegmentComponent } from './components/create-route/segment/segment.component';
import { RouteListComponent } from './components/create-route/route-list/route-list.component';
import { RouteDitailsComponent } from './components/create-route/route-list/route-ditails/route-ditails.component';
import { ClientRouteListComponent } from './components/client-route-list/client-route-list.component';
import { CarrierTransportComponent } from './components/transport/carrier-transport/carrier-transport.component';
import { AddTrpComponent } from './components/transport/carrierTransport/add-trp/add-trp.component';
import { MyTrpComponent } from './components/transport/carrierTransport/my-trp/my-trp.component';
import { BackpackComponent } from './components/backpack/backpack/backpack.component';
import { TableUsersComponent } from './components/setting/table-users/table-users.component';
import { ProfileComponent } from './components/setting/profile/profile.component';
import { CreateUserComponent } from './components/setting/create-user/create-user.component';

const routes: Routes = [
  {                                                                   // <<<--
    path: 'home',                                                     // <<<--
    component: HomeComponent                                          // <<<--
},                                                                    // <<<--
{                                                                     // <<<--
    path: 'user',                                                     // <<<--
    component: UserComponent                                          // <<<--
},                                                                    // <<<--
{                                                                     // <<<--
    path: 'admin',                                                    // <<<--
    component: AdminComponent                                         // <<<--
},                                                                    // <<<--
{                                                                     // <<<--
    path: 'auth/login',                                               // <<<--
    component: LoginComponent                                         // <<<--
},                                                                    // <<<--
{                                                                     // <<<--
    path: 'signup',                                                   // <<<--
    component: SignupComponent                                        // <<<--
},                                                                    // <<<--
{                                                                     // <<<--
    path: '',                                                         // <<<--
    redirectTo: 'home',                                               // <<<--
    pathMatch: 'full'                                                 // <<<--
}, 
{                                                                     // <<<--
    path: 'delivery',                                                   // <<<--
    component: DeliveryComponent                                     // <<<--
}, 
{
    path: 'delivery/routes/:id',
    component: ClientRouteListComponent
},
{                                                                     // <<<--
    path: 'bidClient',                                                   // <<<--
    component: BidClientComponent,
    children: [{
        path: 'details/:id',
        component: BidClientDetailsComponent
    },{
        path: 'backpack/:username',
        component: BackpackComponent
    }]                                        // <<<--
},  
{                                                                     // <<<--
    path: 'settings',                                                   // <<<--
    component: SettingComponent,                                        // <<<--
    children: [
        { path: 'users-list', component: TableUsersComponent },
        { path: 'profile', component: ProfileComponent },
        { path: 'create-user', component: CreateUserComponent}
    ]
},
{
    path: 'delivery/addDelivery',
    component: AddDeliveryComponent
},
{
    path: 'route/:id',
    component: CreateRouteComponent
},
{ 
    path: 'bidClient/routeList/:id', component: RouteListComponent,
    // children: [{
    //     path: 'RouteDitails',
    //     component: RouteDitailsComponent
    // }]
},
{
    path: 'carrierTransport', component: CarrierTransportComponent,
    children: [
        { path: 'add/:id', component: AddTrpComponent, },
        { path: 'my-transport/:id', component: MyTrpComponent, }
    ]
}

];

@NgModule({
    // declarations: [

    //     CreateRouteComponent,
    //     SegmentComponent
    
    //   ],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  entryComponents: [

    SegmentComponent,  

  ],
})
export class AppRoutingModule { }
