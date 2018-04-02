package com.mj.tomcat.ex03;

import java.io.File;

public interface Constants {

	String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	String Package = "com.mj.tomcat.ex03.connector.http";

	int DEFAULT_CONNECTION_TIMEOUT = 60000;

	int PROCESSOR_IDLE = 0;

	int PROCESSOR_ACTIVE = 1;

}
