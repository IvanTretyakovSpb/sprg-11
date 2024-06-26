package com.tretyakov;

import com.tretyakov.model.Book;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BookProvider {

    private final WebClient webClient;
//    private final EurekaClient eurekaClient;

    public BookProvider(ReactorLoadBalancerExchangeFilterFunction balancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(balancerExchangeFilterFunction)
                .build();
//        this.eurekaClient = eurekaClient;
    }

    public Book getRandomBook() {
        Book randomBook = webClient.get()
                .uri("http://book-service/api/book/random")
                .retrieve()
                .bodyToMono(Book.class)
                .block();

        return randomBook;
    }

//    private String getBookServiceIp() {
//        Application application = eurekaClient.getApplication("BOOK-SERVICE");
//        List<InstanceInfo> instances = application.getInstances();
//        int randomIndex = ThreadLocalRandom.current().nextInt(instances.size());
//        InstanceInfo randomInstance = instances.get(randomIndex);
//
//        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort();
//    }

//    @Data
//    private static class BookResponse {
//        private UUID id;
//    }
}
