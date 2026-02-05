package com.emts.domain;

import com.emts.domain.models.Table;
import com.emts.domain.repositories.*;
import com.emts.util.PhoneNumber;

public class Restaurant {

    private String name ;
    private final PhoneNumber phoneNumber;

    private static final TableRepository tables = TableRepository.getInstance();
    private static final CustomerRepository customers = CustomerRepository.getInstance();
    private static final ReservationRepository reservations = ReservationRepository.getInstance();
    private static final BillRepository bills = BillRepository.getInstance();
    private static final OrderRepository orders = OrderRepository.getInstance();
    private static final MenuRepository menu = MenuRepository.getInstance();
    private static final WaiterRepository waiters = WaiterRepository.getInstance();
    private static final OrderItemRepository orderItems = OrderItemRepository.getInstance();
    private static final MenuItemRepository menuItems = MenuItemRepository.getInstance();
    private static final GroupMenuItemRepository groupMenuItems = GroupMenuItemRepository.getInstance();


    public Restaurant(String name, String phoneNumber, int tableCapacity) {
        this.name = name;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.tables.create(new Table(tableCapacity));
    }

    public String getName() {return name;}
    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public PhoneNumber getPhoneNumber() {return phoneNumber;}
    public Restaurant setPhoneNumber(String value){
        phoneNumber.setValue(value);
        return this;
    }

    public static TableRepository getTables() {return tables;}
    public static CustomerRepository getCustomers() {return customers;}
    public static ReservationRepository getReservations() {return reservations;}
    public static BillRepository getBills() {return bills;}
    public static OrderRepository getOrders() {return orders;}
    public static MenuRepository getMenu() {return menu;}
    public static WaiterRepository getWaiters() {return waiters;}
    public static OrderItemRepository getOrderItems() {return orderItems;}
    public static MenuItemRepository getMenuItems() {return menuItems;}
    public static GroupMenuItemRepository getGroupMenuItems() {return groupMenuItems;}

}
