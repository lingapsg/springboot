package com.test.restapp.rest;

import com.test.restapp.domain.Greeting;
import com.test.restapp.domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.marker.Markers.appendEntries;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @ApiOperation(value = "getCustomer", nickname = "getCustomer")
    @RequestMapping(value = "/rest/{type}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Greeting.class)
    })
    public ResponseEntity testRest(@RequestHeader String vendor, @PathVariable String type,
                                   @ApiParam(required = true) @RequestBody User user){
        Map<String, Object> markers = new HashMap<>();
        markers.put("ALARM", "test");
        logger.info(appendEntries(markers),"Reached Controller");
        return ResponseEntity.ok().body(new Greeting(type));
    }
}
