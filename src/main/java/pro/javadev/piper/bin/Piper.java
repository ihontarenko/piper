package pro.javadev.piper.bin;

import pro.javadev.piper.ApplicationContext;
import pro.javadev.piper.ApplicationContextLoader;
import pro.javadev.piper.command.CommandFactory;
import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.execution.ColoredPrinterConsumer;

import static java.lang.String.format;

public class Piper {

    public static void main(String... arguments) {
        try {
            ApplicationContext context = new ApplicationContextLoader().load(arguments);
            CommandFactory.getCommand(context.getCurrentCommandName()).execute(context.getParsedLine());
        } catch (Exception e) {
            ColoredPrinterConsumer consumer = new ColoredPrinterConsumer(AnsiColors.RED_BOLD_BRIGHT, System.out::println);
            consumer.accept(format(" [ %s ] ", e.getMessage()));
        }
    }

}
