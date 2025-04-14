package com.example.ioc;

// 基于静态工厂方法实例化
public class ClientService {
  private static ClientService clientService = new ClientService();
  private ClientService() {}

  public static ClientService createInstance() {
    return clientService;
  }

  public void doWork() {
    System.out.println("ClientService.doWork");
  }
}
