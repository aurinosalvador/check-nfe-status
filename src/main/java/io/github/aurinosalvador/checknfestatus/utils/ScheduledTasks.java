package io.github.aurinosalvador.checknfestatus.utils;

import io.github.aurinosalvador.checknfestatus.entities.NFEServiceStatus;
import io.github.aurinosalvador.checknfestatus.repositories.NFEServiceStatusRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Aurino Salvador
 */

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class.getName());

    @Autowired
    NFEServiceStatusRepository repository;

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 5)
    public void getSefazNfeStatus() {

        try {
            Date now = new Date();

            Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
            List<Element> elements = doc.select("table.tabelaListagemDados tbody tr")
                    .stream()
                    .filter(element -> element.attributes().hasKey("class"))
                    .collect(Collectors.toList());

            for (Element element : elements) {
                String state = element.child(0).text();

                NFEServiceStatus nfeServiceStatus = new NFEServiceStatus();
                nfeServiceStatus.setUpdatedAt(now);
                nfeServiceStatus.setState(state);
                nfeServiceStatus.setService("authorization");
                nfeServiceStatus.setStatus(colorStatusParser(element.child(1)));
                repository.save(nfeServiceStatus);

                nfeServiceStatus = new NFEServiceStatus();
                nfeServiceStatus.setUpdatedAt(now);
                nfeServiceStatus.setState(state);
                nfeServiceStatus.setService("authorizationReturn");
                nfeServiceStatus.setStatus(colorStatusParser(element.child(2)));
                repository.save(nfeServiceStatus);


                nfeServiceStatus = new NFEServiceStatus();
                nfeServiceStatus.setUpdatedAt(now);
                nfeServiceStatus.setState(state);
                nfeServiceStatus.setService("cancelled");
                nfeServiceStatus.setStatus(colorStatusParser(element.child(3)));
                repository.save(nfeServiceStatus);

                nfeServiceStatus = new NFEServiceStatus();
                nfeServiceStatus.setUpdatedAt(now);
                nfeServiceStatus.setState(state);
                nfeServiceStatus.setService("protocolQuery");
                nfeServiceStatus.setStatus(colorStatusParser(element.child(4)));
                repository.save(nfeServiceStatus);

                nfeServiceStatus = new NFEServiceStatus();
                nfeServiceStatus.setUpdatedAt(now);
                nfeServiceStatus.setState(state);
                nfeServiceStatus.setService("statusService");
                nfeServiceStatus.setStatus(colorStatusParser(element.child(5)));
                repository.save(nfeServiceStatus);

                nfeServiceStatus = new NFEServiceStatus();
                nfeServiceStatus.setUpdatedAt(now);
                nfeServiceStatus.setState(state);
                nfeServiceStatus.setService("registrationQuery");
                nfeServiceStatus.setStatus(colorStatusParser(element.child(7)));
                repository.save(nfeServiceStatus);

                nfeServiceStatus = new NFEServiceStatus();
                nfeServiceStatus.setUpdatedAt(now);
                nfeServiceStatus.setState(state);
                nfeServiceStatus.setService("eventReceiver");
                nfeServiceStatus.setStatus(colorStatusParser(element.child(8)));
                repository.save(nfeServiceStatus);

            }

            log.info("Total: {}", elements.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String colorStatusParser(Element element) {
        String source = element.select("img").attr("src");
        if (source.contains("verde")) {
            return "green";
        } else if (source.contains("vermelha")) {
            return "red";
        } else if (source.contains("amarela")) {
            return "yellow";
        } else {
            return "Don't match!";
        }
    }
}
