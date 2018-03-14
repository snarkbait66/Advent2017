package util;

public class BitInstruction {
    public BitCommand cmd;
    public String register;
    public long value;
    public String toRegister;
    public long toValue;

    public BitInstruction(BitCommand cmd, String register, long value, String toRegister, long toValue) {
        this.cmd = cmd;
        this.register = register;
        this.value = value;
        this.toRegister = toRegister;
        this.toValue = toValue;
    }

    @Override
    public String toString() {
        return "BitInstruction{" +
                "cmd=" + cmd +
                ", register='" + register + '\'' +
                ", value=" + value +
                ", toRegister='" + toRegister + '\'' +
                ", toValue=" + toValue +
                '}';
    }
}
