package Advent2017;

public class Instruction {
    Command cmd;
    char register;
    long x;
    long y;
    char toRegister;
    public Instruction(Command cmd, char register, long x, long y, char toRegister) {
        this.cmd = cmd;
        this.register = register;
        this.x = x;
        this.y = y;
        this.toRegister = toRegister;
    }

}
