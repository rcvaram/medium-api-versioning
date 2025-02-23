package com.medium.rcvaram.apiversioning.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.medium.rcvaram.apiversioning.util.Version.V1;
import static com.medium.rcvaram.apiversioning.util.Version.V2;

@Component
@Slf4j
public class VersionProvider {

    public Version identifyVersion(String version) {
        if ("application/vnd.rcvaram.v2+json".equalsIgnoreCase(version.trim())) {
            log.info("Request is identified as V2");;
            return V2;
        }
        log.info("Request is identified as V1");;
        return V1;
    }
}
