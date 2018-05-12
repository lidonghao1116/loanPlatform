/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.template;

import com.platform.loan.pojo.request.BaseRequest;
import com.platform.loan.pojo.result.BaseResult;

/**
 *
 * @author caogu.wyp
 * @version $Id: Processor.java, v 0.1 2018-05-12 下午3:03 caogu.wyp Exp $$
 */
public interface Processor<R extends BaseRequest, N extends BaseResult> {

    public void process(R r, N n, Object... others) throws Exception;
}
