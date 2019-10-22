package org.ashish.springboot.practice.controller;

import org.ashish.springboot.practice.contract.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {
    Logger serviceLogger = LoggerFactory.getLogger(IService.client.getClass().getSimpleName());
}
