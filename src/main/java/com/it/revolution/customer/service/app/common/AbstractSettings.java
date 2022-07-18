package com.it.revolution.customer.service.app.common;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public abstract class AbstractSettings {

    @PostConstruct
    public void postConstruct() {
        log.info("Settings loaded, name = {}, payload = {}", SpringUtil.getClassName(this), this);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
