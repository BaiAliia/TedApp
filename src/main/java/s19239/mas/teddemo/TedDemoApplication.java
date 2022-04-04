package s19239.mas.teddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import s19239.mas.teddemo.gui.controller.MainWindowController;

import javax.swing.*;

@SpringBootApplication
public class TedDemoApplication {

    //public static void main(String[] args) {
  //      SpringApplication.run(TedDemoApplication.class, args);
  //  }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(TedDemoApplication.class).headless(false)
                .run(args);

        SwingUtilities.invokeLater(() -> {
            ctx.getBean(MainWindowController.class).showGUI();
        });
    }

}
