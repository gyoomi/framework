/**
 * Copyright © 2019, Glodon Digital Supplier BU.
 * <p>
 * All Rights Reserved.
 */

package com.gyoomi.adam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The description of class
 *
 * @author Leon
 * @date 2019-10-17 14:59
 */
@RestController
public class TestController {

    @GetMapping(value = "/index")
    public String test() {
        return "hello framework 2.0 ";
    }
}
