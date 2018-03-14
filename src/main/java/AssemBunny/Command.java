package AssemBunny;

/**
 * @author /u/Philboyd_Studge on 12/28/2016.
 */
public enum Command {
    cpy((x, y) -> x, Command::copy),
    inc((x, y) -> x + 1, Command::simple),
    dec((x, y) -> x - 1, Command::simple),
    jnz(Command::jump, Command::jnz),
    tgl((x, y) -> x + y, Command::toggle),
    mul((x, y) -> x * y, Command::mult),
    out((x, y) -> x, Command::out);

    // the Action will actually perform the function. This is set up as a BiFunction, however
    // two arguments are not needed in every case. This is called by each executor.
    private Action action;

    // the Executor will take an Instruction and the current program index
    // and properly parse the information to send to the Action function
    // and return the new index back to the program
    public Executor executor;

    // for day 23 'tgl' function
    public Command opposite;

    static {
        inc.opposite = dec;
        cpy.opposite = jnz;
        jnz.opposite = cpy;
        dec.opposite = inc;
        tgl.opposite = inc;
    }

    Command(Action action,
            Executor executor) {
        this.action = action;
        this.executor = executor;
    }

    // simple instruction x = from register y = value
    static int simple(Instruction inst, int index) {
        inst.getFromRegister().setValue(inst.getCommand().action.apply(inst.getFromRegister().getValue(), 0));
        return ++index;
    }

    // copy : x = From register or value, y = To register
    // if To register is null, ignore and increment index
    static int copy(Instruction inst, int index) {
        if (inst.getToRegister() != null) {
            if (inst.getFromRegister() != null) {
                inst.getToRegister().setValue(inst.getCommand()
                        .action.apply(inst.getFromRegister().getValue(), 0));
            } else {
                inst.getToRegister().setValue(inst.getCommand()
                        .action.apply(inst.getValue(), 0));
            }
        }
        return ++index;
    }

    //jump Action : check for 0, otherwise return new index offset
    static int jump(int nonZero, int offset) {
        if (nonZero == 0) return 1;
        return offset;
    }

    // jnz Executor: x = From register or value y = offset or To register 'value'
    static int jnz(Instruction inst, int index) {
        if (inst.getToRegister() != null) inst.setOffset(inst.getToRegister().getValue());
        if (inst.getFromRegister() == null) {
            index += inst.getCommand().action.apply(inst.getValue(), inst.getOffset());
        } else {
            index += inst.getCommand().action.apply(inst.getFromRegister().getValue(), inst.getOffset());
        }
        return index;
    }

    // toggle Executor : x = From register value, y = index
    static int toggle(Instruction inst, int index) {
        inst.setToggleIndex(inst.getCommand().action.apply(inst.getFromRegister().getValue(), index));
        return ++index;
    }

    // mult Executor : x = From register value y = To register value
    static int mult(Instruction inst, int index) {
        inst.getMultRegister().setValue(inst.getCommand().action.apply(inst.getFromRegister().getValue(),
                inst.getToRegister().getValue()));
        return index + 6;
    }

    // out Executor
    static int out(Instruction inst, int index) {

        // use value for counter
        inst.setValue(inst.getValue() + 1);

        // use offset to store binary output
        inst.setOffset((inst.getOffset() << 1) | inst.getFromRegister().getValue());
        return ++index;
    }
}
