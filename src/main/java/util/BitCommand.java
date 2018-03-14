package util;

import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

public enum BitCommand {
    SET((x, y) -> y),
    AND((x, y) -> x & y),
    OR((x, y) -> x | y),
    XOR((x, y) -> x ^ y),
    SHL((x, y) -> x << y),
    SHR((x, y) -> x >> y),
    NOT((x, y) -> ~x),
    JGZ((x, y) -> x > 0 ? y : 1),
    JZR((x, y) -> x == 0 ? y : 1),
    JNZ((x, y) -> x != 0 ? y : 1),
    JMP((x, y) -> y),
    XGY((x, y) -> x > y ? 1 : 0),
    XGE((x, y) -> x >= y ? 1 : 0),
    RET((x, y) -> y);

    LongBinaryOperator function;

    BitCommand(LongBinaryOperator function) {
        this.function = function;
    }

    public long apply(long x, long y) {
        return function.applyAsLong(x, y);
    }
}
