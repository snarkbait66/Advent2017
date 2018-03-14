package AssemBunny;

import java.util.ArrayList;
import java.util.List;

/**
 * @author /u/Philboyd_Studge on 12/28/2016.
 */
public class Instruction {
    private Command command;
    private Register fromRegister;
    private Register toRegister;
    private Register multRegister; // for day 23
    private int value;
    private int offset;
    private int toggleIndex; // for day 23

    public Instruction(Command command, Register fromRegister, Register toRegister,
                       Register multRegister, int value, int offset) {
        this.command = command;
        this.fromRegister = fromRegister;
        this.toRegister = toRegister;
        this.multRegister = multRegister;
        this.value = value;
        this.offset = offset;
    }

    public Register getFromRegister() { return fromRegister; }
    public Register getToRegister() { return toRegister; }
    public Register getMultRegister() { return multRegister; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public Command getCommand() { return command; }
    public void setCommand(Command command) { this.command = command; }
    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }
    public int getToggleIndex() { return toggleIndex; }
    public void setToggleIndex(int toggleIndex) { this.toggleIndex = toggleIndex; }

    public static List<Instruction> parseInstructions(List<String[]> input, Register[] register) {
        List<Instruction> program = new ArrayList<>();

        for (String[] line : input) {
            // get command
            Command command = Command.valueOf(line[0]);

            // get next token as a character first
            char reg1 = line[1].charAt(0);

            // if it is a letter, set it as the 'from' register, otherwise set it null
            // and then parse it as an integer value and use for the 'value' field
            Register from = (Character.isLetter(reg1)) ? register[reg1 - 'a'] : null;
            int value = (!Character.isLetter(reg1)) ? Integer.parseInt(line[1]) : 0;

            // create vars for remaining fields
            Register to = null;
            Register mult = null; // only used day 23
            int offset = 0;

            // multiple register/value command
            if (line.length == 3) {
                char reg2 = line[2].charAt(0);
                if (Character.isLetter(reg2)) to = register[reg2 - 'a'];
                if (!Character.isLetter(reg2)) offset = Integer.parseInt(line[2]);
            }

            // 3 register command, only used for 'mul' operation day 23
            if (line.length == 4) {
                char reg2 = line[2].charAt(0);
                char reg3 = line[3].charAt(0);
                to = register[reg2 - 'a'];
                mult = register[reg3 - 'a'];
            }

            program.add(new Instruction(command, from, to, mult, value, offset));
        }
        return program;
    }
}
