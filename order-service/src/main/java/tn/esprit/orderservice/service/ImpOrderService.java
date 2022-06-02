package tn.esprit.orderservice.service;

import com.google.gson.Gson;
import com.stripe.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.orderservice.model.*;

//import tn.esprit.orderservice.repository.CategoryRepository;
import tn.esprit.orderservice.repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImpOrderService implements OrderService {

    @Autowired
    PaymentService paymentService;
    @Autowired
    MaillingService mailingService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookService bookService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(ImpOrderService.class);

    @Transactional
    @Override
    public Order add(Order order) {

        //Checking book disponibility
        for (OrderItem item : order.getItems()) {
            if (item.getQuantity() > bookService.getQuantity(item.getBook().getId())){
                return null;
            }
        }
        for (OrderItem item : order.getItems()) {
            if (item.getQuantity() == bookService.getQuantity(item.getBook().getId())) {
                mailingService.sendReptureStockEmail(item.getBook());
            }
        }

        Customer c = paymentService.getCustomer(order.getUser().getEmail());
        String pay_id = paymentService.chargeCustomer(c.getId(), (int) order.getTotalPrice() * 100);
        if (pay_id == null) {
            log.warn("No Enough money !!!");
            return null;
        } else {
            order.setPaymentID(pay_id);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        order.setOrderDate(dtf.format(now));
        Order insertedOrder = orderRepository.save(order);
        for (OrderItem i : order.getItems()) {
            i.setOrder(insertedOrder);
            orderItemService.add(i);
        }
        mailingService.sendReceiptEmail(insertedOrder);
        System.out.println(insertedOrder);
        return insertedOrder;
    }

    @Transactional
    @Override
    public Order update(Order order) {
        System.out.println("*************** In Update ***************");
        System.out.println(order);
        orderRepository.save(order);
        for (OrderItem i : order.getItems()) {
            i.setOrder(new Order(order.getId()));
            orderItemService.update(i);
        }
        return null;
    }

    @Transactional
    @Override
    public Order updateStatus(Order order) {
        System.out.println("*************** In Update Status ***************");
        orderRepository.updateStatus(order.getStatus(), order.getId());
        return order;
    }


    @Transactional
    @Override
    public Order getOrder(int orderId) {
        Optional<Order> res = orderRepository.findById(orderId);
        if (res.isPresent())
            return res.get();
        else
            return null;
    }

    @Transactional
    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public List<Order> getOrders(String status) {
        return orderRepository.findAllByStatus(status);
    }

    @Transactional
    @Override
    public List<Order> getUserOrders(int userId) {
        return orderRepository.findAllByUser(userId);
    }

    @Transactional
    @Override
    public Map<String, Float> getStatsPerGov() {
        Map<String, Float> res = new HashMap<>();
//        ArrayList<String> list = orderRepository.
//        for (String s : list) {
//            res.put(s.split(":")[0], Float.parseFloat(s.split(":")[1]));
//        }
        return res;
    }

    @Override
    public Map<Integer, Double> getStatsPerMonth() {
        Map<Integer, Double> res = new HashMap<>();
        List<Object[]> list = orderRepository.getInputsByMonth();
        for (Object[] obj : list) {
            int month = (int) obj[0];
            double sum = (double) obj[1];
            res.put(month, sum);
        }
        return res;
    }

    @Override
    public Map<Integer, Double> getStatsPerQuarter() {
        Map<Integer, Double> res = new HashMap<>();
        List<Object[]> list = orderRepository.getInputsByQuarter();
        for (Object[] obj : list) {
            int quarter = (int) obj[0];
            double sum = (double) obj[1];
            res.put(quarter, sum);
        }
        return res;
    }

    @Override
    public Map<User, Double> getBestCustomer() {
        Map<User, Double> res = new HashMap<>();

        for (Object[] obj : orderRepository.getBestCustomer()) {
            res.put(userService.GetUser(Long.parseLong(obj[0].toString())), (double) obj[1]);
            return res;
        }
        return null;
    }

    @Override
    public Map<String, List<User>> getClientsFavouriteCategories() {

        Map<String, List<User>> res = new HashMap<>();
        List<Object[]> list = orderRepository.getClientsFavouriteCategories();
        Map<Integer, List<Object[]>> map = list.stream().collect(Collectors.groupingBy(objects -> Integer.parseInt(objects[1].toString())));
        for (Integer i : map.keySet()) {
            Collections.sort(map.get(i),((o1, o2) -> Integer.parseInt(o2[2].toString())  - Integer.parseInt(o1[2].toString())));
            for(Object[] obj : map.get(i)){
                String catName = (String) obj[0];
                if (res.get(catName) == null){
                    ArrayList<User> tmp = new ArrayList<>();
                    tmp.add(userService.GetUser(Long.parseLong(i.toString())));
                    res.put(catName,tmp);
                }else {
                    res.get(catName).add(userService.GetUser(Long.parseLong(i.toString())));
                }
                break;
            }
        }
        return res;
    }
}
