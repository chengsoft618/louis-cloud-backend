
package com.louis.core.repository.callback;



import com.louis.common.api.search.Searchable;

import javax.persistence.Query;

/**
 * @author John·Louis
 * @date 2019年5月30日22:53:36
 */
public interface SearchCallback {

     SearchCallback NONE = new NoneSearchCallback();
     SearchCallback DEFAULT = new DefaultSearchCallback();


    /**
     * 动态拼HQL where、group by having
     *
     * @param ql
     * @param search
     */
     void prepareQL(StringBuilder ql, Searchable search);

     void prepareOrder(StringBuilder ql, Searchable search);

    /**
     * 根据search给query赋值及设置分页信息
     *
     * @param query
     * @param search
     */
     void setValues(Query query, Searchable search);

     void setPageable(Query query, Searchable search);

}
