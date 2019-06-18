package ru.beru;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("JavadocType")
public class AllureTestListener extends TestSettings implements StepLifecycleListener {

    private String pageScreen() {
        File screenshot = ((TakesScreenshot) TestSettings.getDriver())
                .getScreenshotAs(OutputType.FILE);
        Date dat = new Date();
        DateFormat formatForDateNow = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
        String name = formatForDateNow.format(dat);
        try {
            // файл сохраняется как временный на время работы программы
            BufferedImage img = ImageIO.read(screenshot);
            File to = new File(TestSettings.getPATH() + "\\" +  name + ".png");
            ImageIO.write(img, "png", to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    private void addAttachment(final StepResult result) {
        Attachment att = new Attachment();
        att.setType("image/png");
        att.setSource(TestSettings.getPATH() + "\\" + pageScreen() + ".png");
        result.withAttachments(att);
    }

    @Override
    public void beforeStepStart(final StepResult result) {
       addAttachment(result);
    }

    @Override
    public void beforeStepStop(final StepResult result) {
        addAttachment(result);
    }
}