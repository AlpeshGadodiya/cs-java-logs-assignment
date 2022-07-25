package org.cs.log.assignment;

import com.google.gson.Gson;
import org.cs.log.assignment.domain.LogEvent;
import org.cs.log.assignment.model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class LogEventApplication {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static final Logger LOGGER = LoggerFactory.getLogger(LogEventApplication.class);
    public static void main( String[] args ) {
        if(args == null || args.length == 0) {
            LOGGER.error("Path is empty");
            return;
        }
        LOGGER.info("Processing started for file: " + args[0]);
        processFile(args[0]);
        LOGGER.info("Processing finished for file: " + args[0]);
    }

    public static void processFile(String path) {
        Map<String, LogEvent> map = new HashMap<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        File file = new File("src/main/resources/logfile.txt");
        String absolutePath = file.getAbsolutePath();
        try (Stream<String> lines = Files.lines(Paths.get(absolutePath.replaceAll("\\\\", "\\\\\\\\")))) {
            lines.forEach(line -> persistEvent(map,line,session));
        } catch (Exception e) {
            LOGGER.error("File not found exception: {}",e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private static void persistEvent(Map<String,LogEvent > map, String line, Session session) {
        LogEvent logEvent = new Gson().fromJson(line, LogEvent.class);
        LogEvent previous = map.putIfAbsent(logEvent.getId(),logEvent);
        if(previous!= null) {
            Event event = getEventFromLogs(previous, logEvent);
            session.persist(event);
            LOGGER.debug(String.valueOf(event));
            map.remove(previous.getId());
        }
    }

    private static Event getEventFromLogs(LogEvent event1, LogEvent event2) {
        Event event = new Event();
        event.setId(event1.getId());
        event.setDuration(calculateTime(event1.getTimestamp(),event2.getTimestamp()));
        event.setHost(event1.getHost());
        event.setType(event1.getType());
        event.setAlert(event.getDuration()>4);
        return event;
    }

    private static long calculateTime(long l1, long l2) {
        return l1 > l2 ?  l1-l2 : l2-l1;
    }
}
