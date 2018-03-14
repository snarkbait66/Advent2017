package Advent2017;

import java.util.function.LongBinaryOperator;

public enum Command {
    snd((x, y) -> x),
    add((x, y) -> x + y),
    sub((x, y) -> x - y),
    mul((x, y) -> x * y),
    set((x, y) -> y),
    mod((x, y) -> x % y),
    rcv((x, y) -> x),
    jgz((x, y) -> x > 0 ? y : 1),
    npr((x, y) -> Command.isComposite((int) y) ? 0 : 1),
    jnz((x, y) -> x != 0 ? y : 1);


    LongBinaryOperator func;

    Command(LongBinaryOperator func) { this.func = func;}

    long apply(long x, long y) {
        return this.func.applyAsLong(x, y);
    }

    static boolean isComposite(int n) {
        for (int i = 2; i * i <= n ; i++) {
            if (n % i == 0) return true;
        }
        return false;

    }

}
