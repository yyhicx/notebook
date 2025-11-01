package com.example.ioc;

class ClientServiceImpl {
  public ClientServiceImpl() {}

  public void doWork() {
    System.out.println("DefaultServiceLocator.ClientServiceImpl.doWork");
  }
}

// 基于实例工厂方法实例化
public class DefaultServiceLocator {
  private static ClientServiceImpl clientService = new ClientServiceImpl();

  public ClientServiceImpl createClientServiceInstance() {
    return clientService;
  }
}
