package com.hongweiyi.cql.parse.expr.arith;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface UnaryOperator {
    public Object doInteger(Integer o);

    public Object doLong(Long o);

    public Object doFloat(Float o);

    public Object doDouble(Double o);

    public Object doBigDecimal(BigDecimal o);

    public Object doBigInteger(BigInteger o);
}
