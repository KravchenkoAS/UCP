import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './components/admin/admin.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserComponent } from './components/user/user.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { httpInterceptorProviders } from './auth/auth-interceptor';
import { DeliveryComponent } from './components/delivery/delivery.component';
import { BidClientComponent } from './components/bid-client/bid-client.component';
import { SettingComponent } from './components/setting/setting.component';
import { AddDeliveryComponent } from './components/delivery/add-delivery/add-delivery.component';

import { ReactiveFormsModule } from '@angular/forms';
import { DeliveryListComponent } from './components/delivery/delivery-list/delivery-list.component';
import { DeliveryDetailsComponent } from './components/delivery/delivery-details/delivery-details.component';
import { BidClientListComponent } from './components/bid-client/bid-client-list/bid-client-list.component';
import { BidClientDetailsComponent } from './components/bid-client/bid-client-details/bid-client-details.component';
import { CreateRouteComponent } from './components/create-route/create-route.component';
import { SegmentComponent } from './components/create-route/segment/segment.component';
import { FuelComponent } from './components/fuel/fuel/fuel.component';
import { TransportComponent } from './components/transport/transport/transport.component';
import { RouteListComponent } from './components/create-route/route-list/route-list.component';
import { RouteDitailsComponent } from './components/create-route/route-list/route-ditails/route-ditails.component';
import { ClientRouteListComponent } from './components/client-route-list/client-route-list.component';
@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    UserComponent,
    DeliveryComponent,
    BidClientComponent,
    SettingComponent,
    AddDeliveryComponent,
    DeliveryListComponent,
    DeliveryDetailsComponent,
    BidClientListComponent,
    BidClientDetailsComponent,
    CreateRouteComponent,
    SegmentComponent,
    FuelComponent,
    TransportComponent,
    RouteListComponent,
    RouteDitailsComponent,
    ClientRouteListComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent],
  schemas: [
    NO_ERRORS_SCHEMA
]
})
export class AppModule { }
