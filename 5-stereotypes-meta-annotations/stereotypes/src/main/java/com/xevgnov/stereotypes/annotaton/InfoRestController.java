package com.xevgnov.stereotypes.annotaton;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path="/info", produces = MediaType.TEXT_PLAIN_VALUE)
public @interface InfoRestController {
    
}
