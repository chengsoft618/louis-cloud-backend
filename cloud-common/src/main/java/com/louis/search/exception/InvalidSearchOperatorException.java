
package com.louis.search.exception;


import com.louis.search.SearchOperator;

public final class InvalidSearchOperatorException extends SearchException {

    public InvalidSearchOperatorException(String searchProperty, String operatorStr) {
        this(searchProperty, operatorStr, null);
    }

    public InvalidSearchOperatorException(String searchProperty, String operatorStr, Throwable cause) {
        super("Invalid Search Operator searchProperty [" + searchProperty + "], " +
                "operator [" + operatorStr + "], must be one of " + SearchOperator.toStringAllOperator(), cause);
    }
}
