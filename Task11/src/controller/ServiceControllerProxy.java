package controller;

import model.Service;

public class ServiceControllerProxy implements ServiceController{

    private final ServiceController serviceController;

    ServiceControllerProxy(ServiceController serviceController) {
        this.serviceController = serviceController; 
    }
    
    
    public void addService(String guestName, Service service) {
        try {
            serviceController.addService(guestName, service);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listGuestServicesSortedByPrice(String guestName) {
        try {
            serviceController.listGuestServicesSortedByPrice(guestName);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listGuestServicesSortedByDate(String guestName) {
        try {
            serviceController.listGuestServicesSortedByDate(guestName);
        } catch (Exception e) {
            System.out.println("404");
        }
    }

    public void listServicesSortedByCategoryAndPrice() {
        try {
            serviceController.listServicesSortedByCategoryAndPrice();
        } catch (Exception e) {
            System.out.println("404");
        }
    }

}
    

