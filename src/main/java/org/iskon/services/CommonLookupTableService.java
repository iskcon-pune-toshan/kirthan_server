package org.iskon.services;

import java.util.List;

import org.iskon.models.CommonLookupTable;
import org.iskon.models.CommonLookupTableSearch;


public interface CommonLookupTableService {

	CommonLookupTable addCommonLookupTable(CommonLookupTable role);
	
	List<CommonLookupTable> getCommonLookupTable(CommonLookupTableSearch role);
	
	CommonLookupTable updateCommonLookupTable(CommonLookupTable role);

	void deleteCommonLookupTable(CommonLookupTable role);
	
}

