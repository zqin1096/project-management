package com.jrp.utils;

import org.springframework.stereotype.Service;

// Spring need to load an instance of this class in spring context.
// This class will not be scanned (not in the root package) unless the
// "scanBasePackages" is specified in the ProjectManagementApplication class.
@Service
public class DataCleansingService {

	public DataCleansingService() {
		super();
	}

}
// An instance of the @Component or @Repository class will also be loaded in the spring context.