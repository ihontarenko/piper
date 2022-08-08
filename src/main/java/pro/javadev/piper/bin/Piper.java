package pro.javadev.piper.bin;

import org.apache.commons.cli.*;
import pro.javadev.piper.ApplicationContextLoader;
import pro.javadev.piper.command.CommandFactory;
import pro.javadev.piper.command.CommandLineOptionPresets;
import pro.javadev.piper.command.CommandName;
import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.execution.ColoredPrinterConsumer;

import static java.lang.String.format;

public class Piper {

    public static void main(String... arguments) throws ParseException {
        CommandLineParser parser  = new DefaultParser();
        CommandName       command = arguments != null && arguments.length > 0 ? CommandName.valueFor(arguments[0]) : CommandName.HELP;
        Options           options = CommandLineOptionPresets.toOptions(command);
        CommandLine       line    = parser.parse(options, arguments, false);

        try {
            new ApplicationContextLoader().load(arguments);
            CommandFactory.getCommand(command).execute(line);
        } catch (Exception e) {
            ColoredPrinterConsumer consumer = new ColoredPrinterConsumer(AnsiColors.BLACK_BOLD, System.out::println);
            consumer.setBackground(AnsiColors.RED_BG_BRIGHT);
            consumer.accept(format(" [ %s ] ", e.getMessage().toUpperCase()));
        }
    }

}
