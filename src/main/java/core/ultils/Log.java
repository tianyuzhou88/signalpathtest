package core.ultils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Log
{
    private final Class<?> clazz;
    private  Logger logger;
    /**
     *
     * @param clazz
     */
    public Log(Class<?> clazz)
    {
        this.clazz=clazz;
        this.logger=Logger.getLogger(this.clazz);
        Log.initlog4j();

    }
    private static  void initlog4j()
    {

        Properties prop=new Properties();
        prop.setProperty("log4j.rootLogger", "INFO,CONSOLE,E,F");
        prop.setProperty("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender");
        prop.setProperty("log4j.appender.CONSOLE.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.CONSOLE.layout.ConversionPattern", "[%d{YYYY-MM-dd HH:mm:ss,SSS}] %-5p %c %m%n");

        String src="test-output/log";
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(new Date()).toString();

        File dir = new File(src+"/"+date);
        if (!dir.exists())
        {dir.mkdirs();}
        String filepath=dir.getAbsolutePath()+"/"+"log_"+date+".log";

        prop.setProperty("log4j.appender.E","org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.E.file",filepath);
        prop.setProperty("log4j.appender.E.layout","org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.E.layout.ConversionPattern", "[%d{YYYY-MM-dd HH:mm:ss,SSS}] %-5p %c %m%n");
        prop.setProperty("log4j.appender.F","org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.file.encoding","UTF-8");

        String filepathHtml=dir.getAbsolutePath()+"/"+"log_"+date+".html";
        prop.setProperty("log4j.appender.F.file",filepathHtml);
        prop.setProperty("log4j.appender.F.layout","org.apache.log4j.HTMLLayout");

        PropertyConfigurator.configure(prop);
    }
    public  void info(String message)
    {
        logger.info(message);
    }
    public void warn(String message)
    {
        logger.warn(message);
    }
    public void error(String message)
    {
        logger.error(message);
    }
    public void debug(String message)
    {
        logger.debug(message);
    }

}

