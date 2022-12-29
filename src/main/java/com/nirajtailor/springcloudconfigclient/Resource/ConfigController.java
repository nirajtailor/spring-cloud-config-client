package com.nirajtailor.springcloudconfigclient.Resource;

import com.nirajtailor.springcloudconfigclient.Config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ConfigController {
    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/config")
    public List<String> getConfig(){
        return Arrays.asList(appConfig.getConfigA(), appConfig.getConfigB());
    }

}