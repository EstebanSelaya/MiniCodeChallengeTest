package com.hart.simpleretrofit;

import com.hart.simpleretrofit.dto.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by Esteban on 4/15/16.
 */
public interface TestInterface {

    /**
     * Return sorted list of results by last name.
     * @return List<Result>
     */
    List<Result> sortByLastName();

    /**
     * Categorize results by Name.title
     * Use Name.title as the keys to sort Result List into separate lists by category (Name.title)
     * @return Map of categorized Result Lists
     */
    Map<String, List<Result>> categorizeResults();
}
