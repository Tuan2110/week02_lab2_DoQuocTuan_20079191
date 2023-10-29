package com.example.week2_lab2.ws;

import com.example.week2_lab2.dtos.ProductDTO;
import com.example.week2_lab2.models.Product;
import com.example.week2_lab2.services.ProductService;
import com.example.week2_lab2.services.impl.ProductServiceIplm;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint("/productWebSocket/{id}")
public class ProductWebSocket {

    private static Map<Long, Set<Session>> sessionsMap = new HashMap<>();
    private static Map<Long, ProductDTO> products = new HashMap<>();
    private static ProductService productService = new ProductServiceIplm();

    static {
        for (ProductDTO p : productService.getAllProduct()) {
            products.put(p.getId(), p);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("id") long id) {
        sessionsMap.putIfAbsent(id, new HashSet<>());
        sessionsMap.get(id).add(session);
        sendProduct(session, id);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") Long id) {
        sessionsMap.get(id).remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("id") Long id) {
        try {
            double newPrice = Double.parseDouble(message);
            products.get(id).setPrice(newPrice);
            broadcastProduct(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void sendProduct(Session session, Long id) {
        try {
            session.getBasicRemote().sendText(products.get(id).getPrice() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcastProduct(Long id) {
        for (Session session : sessionsMap.get(id)) {
            sendProduct(session, id);
        }
    }
}
