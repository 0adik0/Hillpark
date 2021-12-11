package com.hillpark.hillpark.server.Response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "done", strict = false)
public class ResultResponse {
    @Element(name = "language")
    private String language;
}
