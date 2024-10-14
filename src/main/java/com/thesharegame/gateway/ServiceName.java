package com.thesharegame.gateway;

public enum ServiceName {
    USER_SERVICE("thesharegame-user-service"),
    PORTFOLIO_SERVICE("thesharegame-portfolio-service"),
    SHARE_SERVICE("thesharegame-share-service");

    public final String serviceName;

    private ServiceName(String serviceName){
        this.serviceName = serviceName;
    }
}
